function main(){
    let carouselItems = $('.carousel-item');
    let carouselIndicators = $('.carousel-indicators');
    let badgeValidator = $('.badge-validator');

    if(carouselItems.length > 0){
        $(carouselIndicators).append(`<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>`);

        for(let c = 1; c < carouselItems.length; c++){
            $(carouselIndicators).append(`<li data-target="#carouselExampleIndicators" data-slide-to="${c}"></li>`)
        }
        carouselItems[0].classList.add("active");

    }

    for(let x = 0; x < badgeValidator.length; x++){
        let spanValue = parseInt(badgeValidator[x].innerText);
        if(spanValue === 0 ) {
            badgeValidator[x].classList.add("badge-danger");
        }else if(spanValue < 10 ){
            badgeValidator[x].classList.add("badge-warning");
        }else if(spanValue > 10){
            badgeValidator[x].classList.add("badge-success");
        }

    }
}

main();