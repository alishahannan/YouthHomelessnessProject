
  let startTime = 10;
  let time = startTime * 60;
  let refreshIntervalId = setInterval(updateCountdown, 1000);

  function updateCountdown() {
      const minutes = Math.floor(time / 60);
      let seconds = time % 60;
      seconds = seconds < 10 ? '0' + seconds : seconds; 
      const contdownEl = document.getElementById("timerSection"); 
      contdownEl.innerHTML = `${minutes}:${seconds}`;
      time--;
      if (time < 0) { //stop the setInterval whe time = 0 for avoid negative time
          clearInterval(refreshIntervalId);
      }
  }