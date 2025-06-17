const circle = document.getElementById('circle');

document.addEventListener('mousemove', (event) => {
  const offsetX = circle.offsetWidth / 2;
  const offsetY = circle.offsetHeight / 2;
  circle.style.left = (event.pageX - offsetX) + 'px';
  circle.style.top = (event.pageY - offsetY) + 'px';
});
