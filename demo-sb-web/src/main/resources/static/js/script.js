// Format price
document.addEventListener('DOMContentLoaded', function() {
    const priceElements = document.getElementsByClassName('price');

    Array.from(priceElements).forEach(priceElement => {
        const price = parseFloat(priceElement.textContent);
        if (!isNaN(price)) {
            if (price >= 1) {
                priceElement.textContent = price.toLocaleString('en-US');
            } else if (price > 0) {
                const parts = price.toString().split('.');
                if (parts.length === 2) {
                    const integerPart = parts[0];
                    let decimalPart = parts[1];

                    decimalPart = decimalPart.replace(/0+$/, '');
                    if (decimalPart === '') decimalPart = '0';

                    const minPrecision = 3;
                    while (decimalPart.length < minPrecision) {
                        decimalPart += '0';
                    }

                    const formattedDecimal = decimalPart.match(/.{1,3}/g).join(',');
                    priceElement.textContent = `0.${formattedDecimal}`;
                }
            } else {
                priceElement.textContent = '0';
            }
        }
    });
});

// Filter Function
function filterTable() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById('filterInput');
    filter = input.value.toUpperCase();
    table = document.querySelector('table');
    tr = table.getElementsByTagName('tr');

    for (i = 1; i < tr.length; i++) {
        td = tr[i].getElementsByTagName('td')[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = '';
            } else {
                tr[i].style.display = 'none';
            }
        }
    }
}

// Format price change
document.addEventListener('DOMContentLoaded', function() {
    const priceChangeElements = document.getElementsByClassName('price-change');

    Array.from(priceChangeElements).forEach(priceChangeElement => {
        const priceChange = parseFloat(priceChangeElement.textContent);
        if (!isNaN(priceChange)) {
            const className = priceChange >= 0 ? 'text-success' : 'text-danger';
            priceChangeElement.className = className;
            if (priceChange >= 1 || priceChange <= -1) {
                priceChangeElement.textContent = priceChange.toLocaleString('en-US');
            } else if (priceChange > 0 || priceChange < 0) {
                const absoluteValue = Math.abs(priceChange);
                const parts = absoluteValue.toString().split('.');
                if (parts.length === 2) {
                    const integerPart = parts[0];
                    let decimalPart = parts[1];
                    decimalPart = decimalPart.replace(/0+$/, '');
                    if (decimalPart === '') decimalPart = '0';
                    const minPrecision = 3;
                    while (decimalPart.length < minPrecision) {
                        decimalPart += '0';
                    }
                    const formattedDecimal = decimalPart.match(/.{1,3}/g).join(',');
                    priceChangeElement.textContent = `${priceChange > 0 ? '+' : ''}0.${formattedDecimal}%`;
                } else {
                    priceChangeElement.textContent = `${priceChange > 0 ? '+' : ''}${absoluteValue}%`;
                }
            } else {
                priceChangeElement.textContent = '0%';
            }
        }
    });
});
