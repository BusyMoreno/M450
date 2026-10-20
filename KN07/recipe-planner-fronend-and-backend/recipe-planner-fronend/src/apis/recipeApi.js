import axios from 'axios';

const BASE_URL = 'http://localhost:8080/recipes';

export const createRecipe = (recipeData) => {
    return axios.post(BASE_URL, recipeData);
};