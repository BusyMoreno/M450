import React, { useEffect, useState } from 'react';
import { Form, Button, Col, Row, Alert } from 'react-bootstrap';
import { useParams, useNavigate } from 'react-router-dom';
import { getRecipeById, updateRecipe } from '../../apis/recipeApi';

const UNITS = ['PIECE', 'GRAMM', 'KILOGRAMM', 'LITRE', 'DECILITRE'];

function EditRecipe() {
    const { recipeId } = useParams();
    const navigate = useNavigate();
    const [errorMessage, setErrorMessage] = useState('');
    const [isSaving, setIsSaving] = useState(false);
    const [formData, setFormData] = useState({
        name: '',
        description: '',
        imageUrl: '',
        ingredients: []
    });

    useEffect(() => {
        getRecipeById(recipeId)
            .then(res => {
                const data = res.data;
                setFormData({
                    name: data.name || '',
                    description: data.description || '',
                    imageUrl: data.imageUrl || '',
                    ingredients: (data.ingredients || []).map((ing, idx) => ({
                        listId: idx + 1,
                        name: ing.name,
                        comment: ing.comment || '',
                        unit: ing.unit || 'PIECE',
                        amount: ing.amount || 1
                    }))
                });
            })
            .catch(err => {
                console.error(err);
                setErrorMessage('Could not load recipe details.');
            });
    }, [recipeId]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const addIngredient = () => {
        setFormData(prev => ({
            ...prev,
            ingredients: [
                ...prev.ingredients,
                { listId: Date.now(), name: '', comment: '', unit: 'PIECE', amount: 1 }
            ]
        }));
    };

    const updateIngredient = (listId, field, value) => {
        setFormData(prev => ({
            ...prev,
            ingredients: prev.ingredients.map(item =>
                item.listId === listId ? { ...item, [field]: value } : item
            )
        }));
    };

    const removeIngredient = (listId) => {
        setFormData(prev => ({
            ...prev,
            ingredients: prev.ingredients.filter(item => item.listId !== listId)
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsSaving(true);
        setErrorMessage('');

        const payload = {
            id: recipeId,
            name: formData.name,
            description: formData.description,
            imageUrl: formData.imageUrl,
            ingredients: formData.ingredients.map(({ listId, amount, ...rest }) => ({
                ...rest,
                amount: parseInt(amount, 10) || 1
            }))
        };

        try {
            await updateRecipe(recipeId, payload);
            navigate('/');
        } catch (error) {
            console.error('Save error:', error);
            setErrorMessage('Could not save changes. Please try again.');
        } finally {
            setIsSaving(false);
        }
    };

    return (
        <div className="bg">
            <div className="m-3">
                <h1 className="h3 bg-dark text-white p-2 mt-2">Edit Recipe</h1>
                {errorMessage && <Alert variant="danger">{errorMessage}</Alert>}

                <Form onSubmit={handleSubmit}>
                    <Form.Group className="mb-2" controlId="recipeName">
                        <Form.Label>Recipe Name:</Form.Label>
                        <Form.Control
                            type="text"
                            name="name"
                            value={formData.name}
                            onChange={handleInputChange}
                            required
                        />
                    </Form.Group>

                    <Form.Group className="mb-2" controlId="recipeDescription">
                        <Form.Label>Description:</Form.Label>
                        <Form.Control
                            as="textarea"
                            rows={3}
                            name="description"
                            value={formData.description}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group className="mb-4" controlId="recipeImageUrl">
                        <Form.Label>Image URL:</Form.Label>
                        <Form.Control
                            type="text"
                            name="imageUrl"
                            value={formData.imageUrl}
                            onChange={handleInputChange}
                        />
                    </Form.Group>

                    <Row className="fw-bold">
                        <Col>Ingredient</Col>
                        <Col>Unit</Col>
                        <Col>Quantity</Col>
                        <Col xs={1}></Col>
                    </Row>
                    <hr />

                    {formData.ingredients.map(ingredient => (
                        <Row key={ingredient.listId} className="mb-2">
                            <Col>
                                <Form.Control
                                    type="text"
                                    value={ingredient.name}
                                    placeholder="Ingredient name"
                                    onChange={(e) => updateIngredient(ingredient.listId, 'name', e.target.value)}
                                    required
                                />
                            </Col>
                            <Col>
                                <Form.Select
                                    value={ingredient.unit}
                                    onChange={(e) => updateIngredient(ingredient.listId, 'unit', e.target.value)}
                                >
                                    {UNITS.map(unit => <option key={unit} value={unit}>{unit}</option>)}
                                </Form.Select>
                            </Col>
                            <Col>
                                <Form.Control
                                    type="number"
                                    min="1"
                                    value={ingredient.amount}
                                    onChange={(e) => updateIngredient(ingredient.listId, 'amount', e.target.value)}
                                    required
                                />
                            </Col>
                            <Col xs={1}>
                                <Button variant="outline-dark" onClick={() => removeIngredient(ingredient.listId)}>
                                    x
                                </Button>
                            </Col>
                        </Row>
                    ))}

                    <Row className="my-3">
                        <Col>
                            <Button variant="warning" onClick={addIngredient}>
                                Add Ingredient
                            </Button>
                        </Col>
                    </Row>

                    <Button variant="primary" type="submit" disabled={isSaving} className="mb-5">
                        {isSaving ? 'Saving...' : 'Save Changes'}
                    </Button>
                </Form>
            </div>
        </div>
    );
}

export default EditRecipe;