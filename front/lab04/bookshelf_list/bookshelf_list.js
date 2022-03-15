import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayBookshelves();
});


function fetchAndDisplayBookshelves() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayBookshelves(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/bookshelf/findAll', true);
    xhttp.send();
}


function displayBookshelves(bookshelf) {
	document.getElementById('create').appendChild(createLinkCell('create new bookshelf', '../bookshelf_add/bookshelf_add.html?bookshelf=' + bookshelf.id));
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    bookshelf.forEach(bookshelf => {
        tableBody.appendChild(createTableRow(bookshelf));
    })
}

function createTableRow(bookshelf) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(bookshelf.name));
	tr.appendChild(createLinkCell('edit', '../bookshelf_edit/bookshelf_edit.html?bookshelf=' + bookshelf.id));
    tr.appendChild(createLinkCell('view shelf', '../bookshelf_view/bookshelf_view.html?bookshelf=' + bookshelf.id));
	tr.appendChild(createLinkCell('create book', '../book_add/book_add.html?bookshelf=' + bookshelf.id));
    tr.appendChild(createButtonCell('delete', () => deleteBook(bookshelf)));
    return tr;
}

function deleteBook(bookshelf) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayBookshelves();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/bookshelf/' + bookshelf.id, true);
    xhttp.send();
}
