import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayBook();
});


function fetchAndDisplayBook() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/bookshelf/' + getParameterByName('bookshelf') + '/books/'
        + getParameterByName('book'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayBook();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/bookshelf/' + getParameterByName('bookshelf') + '/books/'
        + getParameterByName('book'), true);

    const request = {
        'name': document.getElementById('name').value,
        'author': document.getElementById('author').value,
		'genre': document.getElementById('genre').value,
        'cost': parseInt(document.getElementById('cost').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}
