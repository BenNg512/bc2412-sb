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

    Array.from(priceChangeElements).forEach(element => {
        const priceChange = parseFloat(element.textContent);
        if (isNaN(priceChange)) return; // Skip if not a valid number

        // Determine sign and color
        const isPositive = priceChange > 0;
        const sign = isPositive ? '+' : (priceChange < 0 ? '-' : '');
        element.className = isPositive ? 'text-success' : (priceChange < 0 ? 'text-danger' : '');

        // Handle absolute value for formatting
        const absValue = Math.abs(priceChange);
        let formattedText;

        if (absValue >= 1) {
            // Large numbers: format with 2 decimal places and commas
            formattedText = absValue.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
        } else if (absValue > 0) {
            // Small numbers (< 1): format with 4 digits, comma after first
            const parts = absValue.toString().split('.');
            const integerPart = parts[0]; // Always "0" for small numbers
            let decimalPart = parts[1] || '0';

            // Ensure exactly 4 digits
            decimalPart = decimalPart.padEnd(4, '0').slice(0, 4); // Pad or truncate to 4 digits

            // Split into first digit and next 3
            const firstDigit = decimalPart.slice(0, 1); // e.g., "0" from "0543"
            const lastThreeDigits = decimalPart.slice(1, 4); // e.g., "543" from "0543"

            // Format with comma
            formattedText = `0.${firstDigit},${lastThreeDigits}`;
        } else {
            // Zero case: display with 4 zeros
            formattedText = '0.0000';
        }

        // Combine sign and formatted text
        element.textContent = `${sign}${formattedText}`;
    });
});

// percentage change
document.addEventListener('DOMContentLoaded', function() {
    const priceChangeElements = document.getElementsByClassName('percentage-change');

    Array.from(priceChangeElements).forEach(element => {
        const priceChange = parseFloat(element.textContent);
        if (isNaN(priceChange)) return;

        const roundedValue = priceChange.toFixed(3);

        const isPositive = priceChange > 0;
        const sign = isPositive ? '+' : (priceChange < 0 ? '-' : '');
        element.className = isPositive ? 'text-success' : (priceChange < 0 ? 'text-danger' : '');

        element.textContent = `${sign}${Math.abs(roundedValue)}%`;
    });
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
