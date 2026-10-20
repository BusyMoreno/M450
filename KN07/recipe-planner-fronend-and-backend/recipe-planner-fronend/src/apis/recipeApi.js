import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/recipes';

export const getRecipes = () => axios.get(BASE_URL);

export const getRecipeById = (id) => axios.get(`${BASE_URL}/recipe/${id}`);

export const createRecipe = (recipeData) => axios.post(BASE_URL, recipeData);

export const updateRecipe = (id, recipeData) => axios.put(`${BASE_URL}/recipe/${id}`, recipeData);