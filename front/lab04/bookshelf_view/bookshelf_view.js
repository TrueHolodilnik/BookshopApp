import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    createImageCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayBookshelves();
    fetchAndDisplayBooks();
});


function fetchAndDisplayBooks() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayBooks(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/bookshelf/' + getParameterByName('bookshelf') + '/books/findAll', true);
    xhttp.send();
}

function displayBooks(books) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    books.forEach(books => {
        tableBody.appendChild(createTableRow(books));
    })
}

function createTableRow(book) {
    let tr = document.createElement('tr');
	tr.appendChild(createTextCell(book.id));
    tr.appendChild(createTextCell(book.name));
	tr.appendChild(createTextCell(book.author));
	tr.appendChild(createTextCell(book.genre));
	tr.appendChild(createTextCell(book.cost));
    tr.appendChild(createLinkCell('edit', '../book_edit/book_edit.html?bookshelf='
        + getParameterByName('bookshelf') + '&book=' + book.id));
    tr.appendChild(createButtonCell('delete', () => deleteBook(book.id)));
    return tr;
}

function deleteBook(book) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayBooks();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/bookshelf/' + getParameterByName('bookshelf')
        + '/books/' + book, true);
    xhttp.send();
}

function fetchAndDisplayBookshelves() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayBookshelf(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/bookshelf/' + getParameterByName('bookshelf'), true);
    xhttp.send();
}

function displayBookshelf(user) {
    setTextNode('id', user.id);
    setTextNode('name', user.name);
    setTextNode('location', user.location);
}
