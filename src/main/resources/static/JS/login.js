const circle = document.getElementById('circle');

document.addEventListener('mousemove', (event) => {
  const offsetX = circle.offsetWidth / 2;
  const offsetY = circle.offsetHeight / 2;
  circle.style.left = (event.pageX - offsetX) + 'px';
  circle.style.top = (event.pageY - offsetY) + 'px';
});

const audio = document.getElementById('audio');
document.getElementById('play-sound').addEventListener('click', () => { audio.play();});

audio.addEventListener('ended', () => { alert('再生完了しました'); });