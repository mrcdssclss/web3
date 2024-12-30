const graph = document.getElementById("graph")
updateR()

document.getElementById("graph").addEventListener('click', function (ev) {
    const svg = document.getElementById("graph")
    const point = svg.createSVGPoint()

    point.x = ev.clientX
    point.y = ev.clientY
    const svgCoords = point.matrixTransform(svg.getScreenCTM().inverse())

    const r = parseFloat(document.getElementById("mainForm:rInput").value)
    const x = (svgCoords.x - 250) / 100 * r
    const y = -(svgCoords.y - 150) / 100 * r
    document.getElementById("mainForm:xSlider").value = x
    document.getElementById("mainForm:yInput").value = y

    updateX(x);
    updateY(y);
    updateR(r);
    document.getElementById("mainForm:valueButton").click()
});

document.getElementById("mainForm:rInput").addEventListener("input", function (){
    const r = parseFloat(this.value)
    const defaultText = "?"
    document.querySelector("text[x='295']").textContent = isNaN(r) ? defaultText : `${r / 2}`
    document.querySelector("text[x='348']").textContent = isNaN(r) ? defaultText : `${r}`
    document.querySelector("text[x='140']").textContent = isNaN(r) ? defaultText : `-${r}`
    document.querySelector("text[x='190']").textContent = isNaN(r) ? defaultText : `-${r / 2}`
    document.querySelector("text[y='105']").textContent = isNaN(r) ? defaultText : `${r / 2}`
    document.querySelector("text[y='55']").textContent = isNaN(r) ? defaultText : `${r}`
    document.querySelector("text[y='205']").textContent = isNaN(r) ? defaultText : `-${r / 2}`
    document.querySelector("text[y='255']").textContent = isNaN(r) ? defaultText : `-${r}`
})

function updateX(inputX){
    document.getElementById("mainForm:xHidden").value = inputX
}

function updateY(inputY){
    document.getElementById("mainForm:yHidden").value = inputY
}

function updateR(inputR){
    document.getElementById("mainForm:rHidden").value = inputR
}

function mySliderFunction() {
    var sliderValue = document.getElementById('mainForm:xSlider').value
    updateX(sliderValue)
}

function drawCircle(x, y, answer) {
    const svg = document.getElementById("graph")
    const circle = document.createElementNS("http://www.w3.org/2000/svg", "circle")

    const r = parseFloat(document.getElementById("mainForm:rInput").value)
    const xSVG = 250 + (x / r) * 100
    const ySVG = 150 - (y / r) * 100

    circle.setAttribute("cx", xSVG.toString())
    circle.setAttribute("cy", ySVG.toString())
    circle.setAttribute("r", "2")
    circle.setAttribute("class", "circles")
    circle.setAttribute("fill", answer ? "pink" : "#8F4A46")

    svg.appendChild(circle)
}

function isIncorrect(str) {
    return !str || !str.trim() || str.startsWith('.')
}

function validateInputs() {
    const yInput = document.getElementById("mainForm:yInput").value
    const rInput = document.getElementById("mainForm:rInput").value
    let isValid = true
    document.getElementById("Rstatus").innerText = ""
    document.getElementById("Ystatus").innerText = ""


    if (isIncorrect(yInput) || isNaN(yInput) || yInput < -5 || yInput > 3) {
        document.getElementById("Ystatus").innerText = "Некорректное значение для y"
        isValid = false
    }
    if (isIncorrect(rInput) || isNaN(rInput) || rInput < 2 || rInput > 5) {
        document.getElementById("Rstatus").innerText = "Некорректное значение для r"
        isValid = false
    }
    return isValid
}
