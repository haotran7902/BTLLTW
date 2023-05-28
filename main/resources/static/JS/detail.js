// Xử lý phần số lượng(quantity)
const quantityInput = document.querySelector('.detail_quantity-input > input');
const increaValInput = document.querySelector('.detail_quantity-increase');
const decreaValInput = document.querySelector('.detail_quantity-decrease');


increaValInput.onclick = function(){
    quantityInput.value = Number(quantityInput.value) + 1;
    if(quantityInput.value > Number(quantityInput.max)){
        quantityInput.value = quantityInput.max;
    }
}

decreaValInput.onclick = function(){
    quantityInput.value = Number(quantityInput.value) - 1;
    if(quantityInput.value < Number(quantityInput.min)) {
        quantityInput.value = quantityInput.min;
    }
}

// Xử lý khi click vào ngôi sao
const ratingItem = document.querySelectorAll('.detail_rating-item');    
let countRating = 0;
let isRating = []
for(let i = 0; i < 5; i++){
    isRating[i] = false;
}
ratingItem.forEach((e,index) => {
    e.onclick = function () {
        console.log('hi');
        console.log(typeof countRating);

        if(isRating[index]){
            if(countRating > 0){
                countRating -= 1;
            }
            else countRating = 0;
            isRating[index] = false;
            e.classList.remove('active');
            console.log(countRating);
        }
        else{
            isRating[index] = true;
            countRating+=1;
            e.classList.add('active');
            console.log( countRating);
        }
    }
})


