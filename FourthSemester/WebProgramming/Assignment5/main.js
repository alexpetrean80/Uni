let emptyFields = [11, 12, 13, 21, 22, 23, 31, 32, 33];
const boxes = {
  11: document.getElementById("11"),
  12: document.getElementById("12"),
  13: document.getElementById("13"),
  21: document.getElementById("21"),
  22: document.getElementById("22"),
  23: document.getElementById("23"),
  31: document.getElementById("31"),
  32: document.getElementById("32"),
  33: document.getElementById("33"),
};

Object.entries(boxes).forEach(([key, value]) => {
  value.addEventListener("click", clickHandler(key, value));
});

function clickHandler(key, value) {
  return () => {
    if (!value.firstChild) {
      occupyBox("x", key);
      checkGameOver();
      aiStep(boxes);
    }
  };
}

function aiStep() {
  const randomIndex = Math.floor(Math.random() * emptyFields.length);
  occupyBox("o", emptyFields[randomIndex]);
  checkGameOver();
}

function occupyBox(char, key) {
  emptyFields = emptyFields.filter((elem) => elem != key);
  const img = document.createElement("img");
  img.src = `./assets/${char}.png`;
  boxes[key].appendChild(img);
}

function hasPlayerWon(checkFunc) {
  return (
    (checkFunc(11) && checkFunc(12) && checkFunc(13)) ||
    (checkFunc(21) && checkFunc(22) && checkFunc(23)) ||
    (checkFunc(31) && checkFunc(32) && checkFunc(33)) ||
    (checkFunc(11) && checkFunc(21) && checkFunc(31)) ||
    (checkFunc(12) && checkFunc(22) && checkFunc(32)) ||
    (checkFunc(13) && checkFunc(23) && checkFunc(33)) ||
    (checkFunc(11) && checkFunc(22) && checkFunc(33)) ||
    (checkFunc(31) && checkFunc(22) && checkFunc(13))
  );
}

function isBoxX(index) {
  if (boxes[index].firstChild == null) {
    return false;
  }
  if (boxes[index].firstChild.src.includes("assets/x.png")) {
    return true;
  }
  return false;
}

function isBoxO(index) {
  if (boxes[index].firstChild == null) {
    return false;
  }
  if (boxes[index].firstChild.src.includes("assets/o.png")) {
    return true;
  }
  return false;
}

function checkGameOver() {
  if (hasPlayerWon(isBoxX)) {
    alert("YOU WON!!");
  } else if (hasPlayerWon(isBoxO)) {
    alert("YOU LOST!!");
  } else if (emptyFields.length == 0) {
    alert("DRAW");
  }
}
