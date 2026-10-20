import React, { useState } from 'react';
import './AddRecipe.css';
import { Form, Button, Col, Row, Alert } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { createRecipe } from '../../apis/recipeApi';

const UNITS = ['PIECE', 'GRAMM', 'KILOGRAMM', 'LITRE', 'DECILITRE'];

function AddRecipe() {
    const navigate = useNavigate();
    const [listId, setListId] = useState(1);
    const [errorMessage, setErrorMessage] = useState('');
    const [isSubmitting, setIsSubmitting] = useState(false);

    const [formData, setFormData] = useState({
        name: '',
        description: '',
        imageUrl: '',
        ingredients: []
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const addIngredient = () => {
        setFormData(prev => ({
            ...prev,
            ingredients: [
                ...prev.ingredients,
                {
                    listId: listId,
                    name: '',
                    comment: '',
                    unit: 'PIECE',
                    amount: 1
                }
            ]
        }));
        setListId(prevId => prevId + 1);
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
        setErrorMessage('');

        if (!formData.name.trim()) {
            setErrorMessage('Please enter a recipe name.');
            return;
        }

        setIsSubmitting(true);

        const payload = {
            name: formData.name,
            description: formData.description,
            imageUrl: formData.imageUrl,
            ingredients: formData.ingredients.map(({ listId, amount, ...rest }) => ({
                ...rest,
                amount: parseInt(amount, 10) || 1
            }))
        };

        try {
            await createRecipe(payload);
            navigate('/');
        } catch (error) {
            console.error('Error saving recipe:', error);
            setErrorMessage('Could not save recipe. Please check backend connection.');
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <div className="bg">
            <div className="m-3">
                <h1 className="h3 bg-dark text-white p-2 mt-2">Add Recipe</h1>

                {errorMessage && <Alert variant="danger">{errorMessage}</Alert>}

                <Form onSubmit={handleSubmit}>
                    <Form.Group className="mb-2" controlId="formBasicName">
                        <Form.Label>Recipe Name:</Form.Label>
                        <Form.Control
                            type="text"
                            name="name"
                            value={formData.name}
                            onChange={handleInputChange}
                            placeholder="Name"
                            required
                        />
                    </Form.Group>

                    <Form.Group className="mb-2" controlId="formBasicDescription">
                        <Form.Label>Description:</Form.Label>
                        <Form.Control
                            as="textarea"
                            rows={3}
                            name="description"
                            value={formData.description}
                            onChange={handleInputChange}
                            placeholder="Description"
                        />
                    </Form.Group>

                    <Form.Group className="mb-4" controlId="formBasicImageUrl">
                        <Form.Label>Image URL:</Form.Label>
                        <Form.Control
                            type="text"
                            name="imageUrl"
                            value={formData.imageUrl}
                            onChange={handleInputChange}
                            placeholder="URL"
                        />
                    </Form.Group>

                    <Row className="fw-bold">
                        <Col>Ingredient</Col>
                        <Col>Unit</Col>
                        <Col>Quantity</Col>
                        <Col xs={1}></Col>
                    </Row>
                    <hr />

                    {formData.ingredients.map((ingredient) => (
                        <Row key={ingredient.listId} className="mb-2">
                            <Col>
                                <Form.Control
                                    type="text"
                                    placeholder="Name"
                                    value={ingredient.name}
                                    onChange={(e) => updateIngredient(ingredient.listId, 'name', e.target.value)}
                                    required
                                />
                            </Col>
                            <Col>
                                <Form.Select
                                    value={ingredient.unit}
                                    onChange={(e) => updateIngredient(ingredient.listId, 'unit', e.target.value)}
                                >
                                    {UNITS.map(unit => (
                                        <option key={unit} value={unit}>{unit}</option>
                                    ))}
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
                                <Button
                                    variant="outline-dark"
                                    onClick={() => removeIngredient(ingredient.listId)}
                                >
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

                    <Button variant="primary" type="submit" disabled={isSubmitting} className="mb-5">
                        {isSubmitting ? 'Saving...' : 'Submit'}
                    </Button>
                </Form>
            </div>
        </div>
    );
}

export default AddRecipe;