// Получаем ссылку на дисплей калькулятора
const display = document.getElementById('display')

// Функция для добавления числа или оператора на дисплей
function appendToDisplay(value) {
    if (display.value==="0") {
        display.value=value
    }else{
        display.value += value
    }
    
}
// Функция для выполнения операции
function performOperation(operator) {
    // Получаем значение с дисплея
    let currentValue = display.value

    // Проверяем, если на дисплее уже есть выражение
    if (currentValue) {
        // Добавляем оператор к текущему выражению
        appendToDisplay(operator)
    }
}
// Функция для вычисления результата
function calculate() {
    try {
        const result = eval(display.value)
        if (result===NaN){
            display.value = 'Error'
        }
        else {
            display.value=result
        }
    } catch (error) {
        // Если произошла ошибка при вычислении, выводим сообщение об ошибке
        display.value = 'Error'
    }
}

// Функция для очистки дисплея
function clearDisplay() {
    display.value = ''
}