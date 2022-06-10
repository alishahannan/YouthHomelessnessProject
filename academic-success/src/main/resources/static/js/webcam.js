navigator.mediaDevices.getUserMedia({
    video:true,
    width: 1280,
    height: 720
})
.then(stream => {
    document.getElementById("webcam").srcObject = stream;
})