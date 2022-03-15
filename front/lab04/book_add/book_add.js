import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

});

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/bookshelf/' + getParameterByName('bookshelf') + '/books/', true);

    const request = {
        'name': document.getElementById('name').value,
        'author': document.getElementById('author').value,
		'genre': document.getElementById('genre').value,
        'cost': parseInt(document.getElementById('cost').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}
