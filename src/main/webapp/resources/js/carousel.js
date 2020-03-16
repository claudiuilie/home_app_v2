function main(){
    let carouselSearchItems = $('.searchlist-item');
    let carouselSearchIndicators = $('.search-indicators');

    generateCarouselItems(carouselSearchItems, "#carouselExampleIndicators" , carouselSearchIndicators);

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
    let watchlistProgress = $('.watchlist-progress');

    let watchlistProgressElems = [];

    if(watchlistProgress.length > 0){
        for(let i =0 ; i< watchlistProgress.length ; i++){
            watchlistProgressElems.push({"id" : watchlistProgress[i].getAttribute("id")});
            watchlistProgressElems.push({"magnet" : watchlistProgress[i].getAttribute("name")});
        }

        let interval = setInterval(function () {
            postData(`http://localhost:3000/`)
                .then((data) => {
                    if(Object.keys(data).length > 0){
                        for(let k in watchlistProgressElems){
                            if(typeof data[`${watchlistProgressElems[k].id}`] == "object"){
                                let element = watchlistProgressElems[k].id;
                                let innerHtml = data[`${watchlistProgressElems[k].id}`].data[4];

                                $($(`#22`).find('span')[0]).text("Download speed: " + innerHtml + " mb/s")
                                $($(`#22`).find('span')[1]).text("Progress: " +  data[`${watchlistProgressElems[k].id}`].data[5] + " % ")

                            }
                        }
                    }else{
                        console.log("0 "+ data);
                    }
                     // JSON data parsed by `response.json()` call
                })
                .catch((error) => {
                    console.log('Error:', error);
                });
        }, 1000)
    }
}

async function postData(url = '', data = {}) {
    // Default options are marked with *
    const response = await fetch(url, {
        method: 'GET'
    });
    return response.json(); // parses JSON response into native JavaScript objects
}



// clearInterval(interval)