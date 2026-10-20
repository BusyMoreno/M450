import React, { useState } from 'react';
import './AddRecipe.css';
import { Form, Button, Col, Row, Alert } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { createRecipe } from '../../apis/recipeApi';
import AddIngredient from '../AddIngredient/AddIngredient';

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
                    ingredient: '',
                    unit: 'PIECE',
                    quantity: ''
                }
            ]
        }));
        setListId(prevId => prevId + 1);
    };

    const updateIngredient = (ingredientObj) => {
        const updatedIngredients = formData.ingredients.map(item =>
            item.listId === ingredientObj.listId ? ingredientObj : item
        );
        setFormData(prev => ({ ...prev, ingredients: updatedIngredients }));
    };

    const removeIngredient = (ingredientObj) => {
        const updatedIngredients = formData.ingredients.filter(
            item => item.listId !== ingredientObj.listId
        );
        setFormData(prev => ({ ...prev, ingredients: updatedIngredients }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrorMessage('');

        if (!formData.name.trim()) {
            setErrorMessage('Bitte geben Sie einen Rezeptnamen ein.');
            return;
        }

        setIsSubmitting(true);

        // listId entfernen, da das Backend nur ingredient, unit und quantity erwartet
        const payload = {
            name: formData.name,
            description: formData.description,
            imageUrl: formData.imageUrl,
            ingredients: formData.ingredients.map(({ listId, ...rest }) => rest)
        };

        try {
            await createRecipe(payload);
            navigate('/');
        } catch (error) {
            console.error('Fehler beim Speichern:', error);
            setErrorMessage('Rezept konnte nicht gespeichert werden. Läuft das Backend?');
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

                    {formData.ingredients.map(ingredient => (
                        <AddIngredient
                            key={ingredient.listId}
                            ingredient={ingredient}
                            updateIngredient={updateIngredient}
                            removeIngredient={removeIngredient}
                        />
                    ))}

                    <Row className="my-3">
                        <Col>
                            <Button variant="warning" onClick={addIngredient}>
                                Add Ingredient
                            </Button>
                        </Col>
                    </Row>

                    <Button variant="primary" type="submit" disabled={isSubmitting} className="mb-5">
                        {isSubmitting ? 'Wird gespeichert...' : 'Submit'}
                    </Button>
                </Form>
            </div>
        </div>
    );
}

export default AddRecipe;