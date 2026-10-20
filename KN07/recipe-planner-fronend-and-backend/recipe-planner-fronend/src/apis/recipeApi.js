import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/recipes';

export const getRecipes = () => axios.get(BASE_URL);

export const createRecipe = (recipeData) => {
    return axios.post(BASE_URL, recipeData);
};