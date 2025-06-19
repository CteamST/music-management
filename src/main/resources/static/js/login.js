const circle = document.getElementById('circle');

document.addEventListener('mousemove', (event) => {
  const offsetX = circle.offsetWidth / 2;
  const offsetY = circle.offsetHeight / 2;
  circle.style.left = (event.pageX - offsetX) + 'px';
  circle.style.top = (event.pageY - offsetY) + 'px';
});

let lastTime = 0;
const delay = 100; // 影を生成する間隔（ミリ秒）

document.addEventListener('mousemove', function(e) {
  const currentTime = Date.now();
  if (currentTime - lastTime > delay) {
    const shadow = document.createElement('div');
    shadow.className = 'cursor-shadow';
    shadow.style.left = `${e.pageX}px`;
    shadow.style.top = `${e.pageY}px`;
    document.body.appendChild(shadow);

    // 影を徐々に消す
    setTimeout(() => {
      shadow.style.opacity = '0';
    }, 50);

    // DOMから影を削除
    setTimeout(() => {
      document.body.removeChild(shadow);
    }, 550);

    lastTime = currentTime;
  }
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
