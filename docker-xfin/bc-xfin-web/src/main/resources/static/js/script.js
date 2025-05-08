document.addEventListener('DOMContentLoaded', function() {
    let timeLeft = 9;
    const timerElement = document.getElementById('timer');
    function updateTimer() {
        timerElement.textContent = timeLeft;
        timeLeft--;
        if (timeLeft < 0) {
            timeLeft = 10;
        }
    }
    setInterval(updateTimer, 1000);
});