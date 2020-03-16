function main(){
    let carouselSearchItems = $('.searchlist-item');
    let carouselSearchIndicators = $('.search-indicators');

    generateCarouselItems(carouselSearchItems, "#carouselExampleIndicators" , carouselSearchIndicators);


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
