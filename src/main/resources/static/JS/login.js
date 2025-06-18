const circle = document.getElementById('circle');

document.addEventListener('mousemove', (event) => {
  const offsetX = circle.offsetWidth / 2;
  const offsetY = circle.offsetHeight / 2;
  circle.style.left = (event.pageX - offsetX) + 'px';
  circle.style.top = (event.pageY - offsetY) + 'px';
});

window.addEventListener("DOMContentLoaded", () => {
  const title = document.querySelector('.slide-bounce-title');
  const text = "わんわーく";
  title.innerHTML = ''; // 初期化

  [...text].forEach((char, i) => {
    const span = document.createElement('span');
    span.textContent = char;
    span.style.animationDelay = `${i * 0.15}s`;
    title.appendChild(span);
  });
});
