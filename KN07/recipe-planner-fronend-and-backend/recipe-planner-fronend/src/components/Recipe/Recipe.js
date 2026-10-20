import React, { useState } from 'react';
import './Recipe.css';
import { Button, Card, Modal, Form, Alert } from 'react-bootstrap';
import { updateRecipe } from '../../apis/recipeApi';

function Recipe({ recipe, onUpdate }) {
    const [showEdit, setShowEdit] = useState(false);
    const [name, setName] = useState(recipe?.name || '');
    const [description, setDescription] = useState(recipe?.description || '');
    const [imageUrl, setImageUrl] = useState(recipe?.imageUrl || '');
    const [errorMessage, setErrorMessage] = useState('');
    const [isSaving, setIsSaving] = useState(false);

    const handleOpen = () => {
        setName(recipe?.name || '');
        setDescription(recipe?.description || '');
        setImageUrl(recipe?.imageUrl || '');
        setErrorMessage('');
        setShowEdit(true);
    };

    const handleClose = () => setShowEdit(false);

    const handleSave = async (e) => {
        e.preventDefault();
        setIsSaving(true);
        setErrorMessage('');

        const payload = {
            id: recipe.id,
            name,
            description,
            imageUrl,
            ingredients: recipe.ingredients || []
        };

        try {
            await updateRecipe(recipe.id, payload);
            setShowEdit(false);
            if (onUpdate) {
                onUpdate();
            }
        } catch (error) {
            console.error('Error updating recipe:', error);
            setErrorMessage('Could not update recipe. Please try again.');
        } finally {
            setIsSaving(false);
        }
    };

    return (
        <>
            <Card style={{ width: '18rem' }} className="mb-4">
                <Card.Img variant="top" src={recipe?.imageUrl} alt={recipe?.name} />
                <Card.Body>
                    <Card.Title>{recipe?.name}</Card.Title>
                    <Card.Text>{recipe?.description}</Card.Text>
                    <Button variant="primary" onClick={handleOpen}>
                        Edit Details
                    </Button>
                </Card.Body>
            </Card>

            <Modal show={showEdit} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Edit Recipe</Modal.Title>
                </Modal.Header>
                <Form onSubmit={handleSave}>
                    <Modal.Body>
                        {errorMessage && <Alert variant="danger">{errorMessage}</Alert>}

                        <Form.Group className="mb-2" controlId="editName">
                            <Form.Label>Name:</Form.Label>
                            <Form.Control
                                type="text"
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                                required
                            />
                        </Form.Group>

                        <Form.Group className="mb-2" controlId="editDescription">
                            <Form.Label>Description:</Form.Label>
                            <Form.Control
                                as="textarea"
                                rows={3}
                                value={description}
                                onChange={(e) => setDescription(e.target.value)}
                            />
                        </Form.Group>

                        <Form.Group className="mb-2" controlId="editImageUrl">
                            <Form.Label>Image URL:</Form.Label>
                            <Form.Control
                                type="text"
                                value={imageUrl}
                                onChange={(e) => setImageUrl(e.target.value)}
                            />
                        </Form.Group>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose}>
                            Cancel
                        </Button>
                        <Button variant="primary" type="submit" disabled={isSaving}>
                            {isSaving ? 'Saving...' : 'Save Changes'}
                        </Button>
                    </Modal.Footer>
                </Form>
            </Modal>
        </>
    );
}

export default Recipe;