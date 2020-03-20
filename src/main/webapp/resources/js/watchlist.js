
let refreshRun = false;
let refreshInterval = 1000;
let refreshRunner;

async function postData(method, url = '', data = {}) {
    // Default options are marked with *
    const response = await fetch(url, {
        'method': method
    });
    return response.json(); // parses JSON response into native JavaScript objects
}

function fetchTorr(elem) {
    blockCOntrol(elem);
    let flow = elem.textContent.trim();

    switch (flow) {
        case "DOWNLOAD":
            downloadMovie(elem);
            break;
        case "STOP":
            stopDownload(elem);
            break;
        case "RETRY":
            retryDownload(elem);
            break;
        default:
            alert("Invalid flow.")
    }
}

async function downloadMovie(elem) {
    let id = elem.name;
    let magnet = elem.value;

    await postData("POST", `http://localhost:3000/download?id=${id}&magnet=${magnet}`)
        .then((response) => {
            if (response.error) {
                alert(response.error)
                if(response.error !== "Tracker is already posted."){
                    elem.className = "btn btn-block btn-outline-warning";
                    elem.innerText = "RETRY";
                }
            } else {
                if (response.posted) {
                    elem.className = "btn btn-block btn-outline-danger";
                    elem.innerText = "STOP";
                    showProgress(elem)
                } else {
                    alert("Error on posting...")
                }
            }
        })
        .catch((error) => {
            alert('Error:' + error);
        });
    unblockControl(elem);
}

function showProgress(elem) {
    let movieProgress = document.getElementById(`progress_${elem.name}`);
    movieProgress.classList.remove('d-none');
    movieProgress.classList.add('d-block');
    movieProgress.setAttribute("name", '1');
    refreshProgress()
}

function refreshProgress() {

    if (refreshRun === false) {

        refreshRunner = setInterval(function () {

            let blocks = document.getElementsByClassName("watchlist-progress");
            let validProgressBlocks = {};

            for(let x = 0; x< blocks.length ; x++){
                let movieBlockId = blocks[x].getAttribute("value");
                let blockActive = blocks[x].getAttribute("name");
                let blockId = blocks[x].id;
                if(blockActive === '1'){
                    validProgressBlocks[blockId] = {
                        progressBlockDom: blocks[x],
                        progressBlockId: blockId,
                        progressBlockActive: blockActive,
                        progressBlockMovieId : movieBlockId
                    }
                }
            }

            let activeBlocksLength = Object.keys(validProgressBlocks).length;

            if(activeBlocksLength === 0){
                clearInterval(refreshRunner)
            }else{
                refreshRun = true;
                postData('GET', 'http://localhost:3000/')
                    .then((data) => {
                        console.log(data);
                        if (Object.keys(data).length > 0) {
                            for(let k in validProgressBlocks){
                                let progressBlock = validProgressBlocks[k].progressBlockDom;
                                let progressBlockId = validProgressBlocks[k].progressBlockMovieId;
                                let taskResults = data[progressBlockId];

                                if(typeof taskResults !== 'undefined'){
                                    //finish
                                    if(taskResults.data[1] === 1){
                                        let watchlistDeleteButton = document.getElementById(`delete_button_block_${progressBlockId}`);
                                        let watchlistDownloadButton = document.getElementById(`download_button_block_${progressBlockId}`);
                                        let watchlistStopButton = document.getElementById(`stop_button_block_${progressBlockId}`);
                                        watchlistStopButton.classList.remove('d-block');
                                        watchlistStopButton.classList.add('d-none');
                                        watchlistDownloadButton.classList.remove('d-block');
                                        watchlistDownloadButton.classList.add('d-none');
                                        watchlistDeleteButton.classList.remove('d-none');
                                        watchlistDeleteButton.classList.add('d-block');
                                        progressBlock.classList.remove('d-block');
                                        progressBlock.classList.add('d-none');
                                        delete validProgressBlocks[k];

                                        activeBlocksLength = Object.keys(validProgressBlocks).length;
                                        if(activeBlocksLength === 0){
                                            clearInterval(refreshRunner)
                                            refreshRun = false;
                                        }
                                    }else{
                                        progressBlock.children[0].innerText ="Progress: " + taskResults.data[5];
                                        progressBlock.children[1].innerText ="Download speed: " + taskResults.data[4];
                                    }
                                }
                            }
                        } else {

                            console.log("0 " + data);
                        }
                        // JSON data parsed by `response.json()` call
                    })
                    .catch((error) => {
                        console.log('Error:', error);
                    });
            }
        }, refreshInterval);

    } else {
        console.log('already runnning')
        // clearInterval(refreshRunner);
    }
}


function stopDownload(element) {
    console.log("STOP");
}

function retryDownload(elem) {
    console.log("Retry");
}

function blockCOntrol(element) {
    element.disabled = true;
    $(element).append(`<span class="spinner-border spinner-border-sm block-control" role="status" aria-hidden="true"></span>`);
}

function unblockControl(element) {
    element.disabled = false;
    $(element).find('.block-control').remove();
}

refreshProgress();