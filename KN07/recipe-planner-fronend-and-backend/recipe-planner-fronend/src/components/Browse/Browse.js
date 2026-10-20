import React, { useEffect, useState } from 'react';
import './Browse.css';
import Recipe from "../Recipe/Recipe";
import { Col, Row } from "react-bootstrap";
import { getRecipes } from "../../apis/recipeApi";

const Browse = () => {
    const [recipes, setRecipes] = useState(null);

    const loadRecipes = () => {
        getRecipes()
            .then((response) => {
                setRecipes(response.data);
            })
            .catch((error) => {
                console.error("Fehler beim Laden der Rezepte:", error);
            });
    };

    useEffect(() => {
        loadRecipes();
    }, []);

    if (!recipes) return null;

    return (
        <>
            <Row>
                {recipes.map((recipe) => (
                    <Col key={recipe.id} sm={12} md={6} lg={4} xl={3}>
                        <Recipe recipe={recipe} onUpdate={loadRecipes} />
                    </Col>
                ))}
            </Row>
        </>
    );
};

Browse.propTypes = {};
Browse.defaultProps = {};

export default Browse;