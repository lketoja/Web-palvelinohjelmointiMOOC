var url = contextRoot + "tasks"

var http = new XMLHttpRequest()

http.onreadystatechange = function() {
    if (this.readyState != 4) {
        return
    }
    
    console.log(this.responseText)
}
    
function addTask() {
    var data = {
        title: testi
        body: document.getElementById("add-task").value
    }
    
    http.open("POST", url)
    http.send(JSON.stringify(data))
}