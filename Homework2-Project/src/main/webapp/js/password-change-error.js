const formConfirmPassword2 = document.getElementById("formConfirmPassword");
formConfirmPassword2.addEventListener('submit', (e) => {
    if(!formConfirmPassword2.checkValidity()) return;

    e.preventDefault();
    const formData = new FormData(formConfirmPassword2);
    const urlencodedData = new URLSearchParams(formData);
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", rootPath + "/rest/user/password", true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === XMLHttpRequest.DONE) {
            if(xmlhttp.status === 200) {
                const alertPlaceholder = document.getElementById('liveAlertPlaceholder');
                const cancelchanges = document.getElementById('cancel');
                cancelchanges.innerHTML = "Go back to detail";
                bootstrapAlert("Password was modified successfully", 'success', alertPlaceholder);

            }
            else{
                const alertPlaceholder = document.getElementById('liveAlertPlaceholder');
                const cancelchanges = document.getElementById('cancel');
                cancelchanges.innerHTML = "Cancel changes";
                bootstrapAlert(parseError(xmlhttp), 'danger', alertPlaceholder);
            }
        }
    }
    xmlhttp.send(urlencodedData);

});