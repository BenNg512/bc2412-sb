document.addEventListener('DOMContentLoaded', function() {

    // Format price
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

// Format price change
    const priceChangeElements = document.getElementsByClassName('price-change');

    Array.from(priceChangeElements).forEach(element => {
        const priceChange = parseFloat(element.textContent);
        if (isNaN(priceChange)) return;

        const isPositive = priceChange > 0;
        const sign = isPositive ? '▲ ' : (priceChange < 0 ? '▼ ' : '');
        element.className = isPositive ? 'text-success' : (priceChange < 0 ? 'text-danger' : '');

        const absValue = Math.abs(priceChange);
        let formattedText;

        if (absValue >= 1) {
            formattedText = absValue.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
        } else if (absValue > 0) {
            const parts = absValue.toString().split('.');
            const integerPart = parts[0]
            let decimalPart = parts[1] || '0';

            decimalPart = decimalPart.padEnd(4, '0').slice(0, 4);

            const firstDigit = decimalPart.slice(0, 1);
            const lastThreeDigits = decimalPart.slice(1, 4);

            formattedText = `0.${firstDigit},${lastThreeDigits}`;
        } else {
            formattedText = '0.0000';
        }

        element.textContent = `${sign}${formattedText}`;
    });


// formate priceChangePercentage24h (apply-able to all %change)
    const priceChangePercentage24hElements = document.getElementsByClassName('percentage-change');

    Array.from(priceChangePercentage24hElements).forEach(element => {
        const priceChangePercentage24h = parseFloat(element.textContent);
        if (isNaN(priceChangePercentage24h)) return;

        const roundedValue = priceChangePercentage24h.toFixed(3);

        const isPositive = priceChangePercentage24h > 0;
        const sign = isPositive ? '▲ ' : (priceChangePercentage24h < 0 ? '▼ ' : '');
        element.className = isPositive ? 'text-success' : (priceChangePercentage24h < 0 ? 'text-danger' : '');

        element.textContent = `${sign}${Math.abs(roundedValue)}%`;
    });


// timer
let timeLeft = 30;
const timerElement = document.getElementById('timer');
function updateTimer() {
    timerElement.textContent = timeLeft;
    timeLeft--;
    if (timeLeft < 0) {
        timeLeft = 30;
    }
}
setInterval(updateTimer, 1000);

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

});