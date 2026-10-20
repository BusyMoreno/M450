import React from 'react';
import './Recipe.css';
import { Button, Card } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

function Recipe({ recipe }) {
    const navigate = useNavigate();

    return (
        <Card style={{ width: '18rem' }} className="mb-4">
            <Card.Img variant="top" src={recipe?.imageUrl} alt={recipe?.name} />
            <Card.Body>
                <Card.Title>{recipe?.name}</Card.Title>
                <Card.Text>{recipe?.description}</Card.Text>
                <Button variant="primary" onClick={() => navigate(`/edit/${recipe?.id}`)}>
                    Edit Details
                </Button>
            </Card.Body>
        </Card>
    );
}

export default Recipe;