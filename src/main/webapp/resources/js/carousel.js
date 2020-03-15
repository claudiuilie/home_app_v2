function main(){
    let carouselSearchItems = $('.searchlist-item');
    let carouselWatchlistItems = $('.watchlist-item');
    let carouselSearchIndicators = $('.search-indicators');
    let carouselWatchlistIndicators = $('.watchlist-indicators');
    let badgeValidator = $('.badge-validator');

    generateCarouselItems(carouselSearchItems, "#carouselExampleIndicators" , carouselSearchIndicators);
    generateCarouselItems(carouselWatchlistItems, "#carouselExampleIndicators2", carouselWatchlistIndicators);


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

    //de cautat solutie pt activare carusel la watchlist
    function generateCarouselItems(items, carouselId, indicators){
        console.log(items)
        if(items.length > 0){
            $(indicators).append(`<li data-target="${carouselId}" data-slide-to="0" class="active"></li>`);

            for(let c = 1; c < items.length; c++){
                $(indicators).append(`<li data-target="${carouselId}" data-slide-to="${c}"></li>`)
            }
            items[0].classList.add("active");
        }
    }
}

main();

function refreshWatchlist() {
    $('#watchlist').load(window.location.href + " #watchlist");
}
