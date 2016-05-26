
var messages = [];

window.onload = function() {

    loadMesScreen();
    loadLastUser();

};

function sendMessage() {

    if (getUserName() == undefined) {
        alert("You need to input your name");
        return;
    }

    if(getMessageText().length > 100) {
        alert("Too big message!");
        return;
    }
    if(getMessageText().length == 0) {
        alert("You need to input something!");
        return;
    }

    var message = {
        id : uniqueId(),
        date : new Date().toLocaleString(),
        author : getUserName(),
        text : getMessageText()
    };

    addMessageOnUI(message);
    messages.push(message);

    localStorage.setItem("messages", JSON.stringify(messages));

    clearText();

}

function clearText() {
    var inputElement = document.getElementsByClassName('textarea')[0];
    inputElement.value="";
}

function getMessageText() {
    var inputElement = document.getElementsByClassName('textarea')[0];
    return inputElement.value;
}

function changeUser() {

    var newUserName = prompt("Input your name", "Here :");

    document.getElementById("username").innerHTML = "You logged in as : " + newUserName;

    if((newUserName.length > 20) || (newUserName == null) || (newUserName == "")) {
        alert("Incorrect name!");
        return;
    }

    var elements = window.document.getElementsByClassName('users-online-list');
    for(var i = 0; i < elements.length; i++) {
        if(elements[i].innerHTML == newUserName) {
            alert("User with such name is already exist!");
            return;
        }
    }

    localStorage.setItem("userName", newUserName);

    changeUserActivity();

}

function editMessage() {
    var currentText = click().lastChild.innerHTML;
    var newText = prompt("Change your message: ", currentText.toString());

    if(newText == "") {
        alert("You should input something!");
        return;
    }

    if(newText == null) {
        return;
    }

    var newMessageDiv = document.createElement('div');
    newMessageDiv.className = 'message';

    var messageInfoDiv = document.createElement('div');
    messageInfoDiv.className = 'my-message-info';
    messageInfoDiv.innerHTML = getUserName() + " " + new Date().toLocaleString() + ' <a href = "#" class ="change-mes" title = "Edit message" onclick="editMessage()"><img src = "http://s1.iconbird.com/ico/2013/3/636/w80h8013939672873.png" width=20em height=20em></a>' +
        '<a href = "#" class ="delete-mes" title = "Delete message" onclick="deleteMessage()"><img src = "http://www.iconsearch.ru/uploads/icons/49handdrawing/128x128/bin-full.png" width=20em height=20em></a>';

    var messageTextDiv = document.createElement('div');
    messageTextDiv.className = 'my-message-text';
    messageTextDiv.innerHTML = newText;

    if(messageTextDiv.innerHTML.length > 100) {
        alert("Too big message!");
        return false;
    }

    newMessageDiv.appendChild(messageInfoDiv);
    newMessageDiv.appendChild(messageTextDiv);

    var chatPanel = window.document.getElementsByClassName('message-box')[0];
    chatPanel.replaceChild(newMessageDiv,click());


    var index = findMessageIndex(currentText);

    editMessageLS(index, newText);

}

function click() {
    var t = event.target;
    var par = t.parentNode;
    var par2 = par.parentNode;
    return par2.parentNode;
}

function deleteMessage() {
    var newMessageDiv = document.createElement('div');
    newMessageDiv.className = 'del-message';

    var mesInfo = document.createElement('div');
    mesInfo.className = 'del-message-info';
    mesInfo.innerHTML = getUserName() + " " + new Date().toLocaleString();

    var mesText = document.createElement('div');
    mesText.className = 'del-message-text';
    mesText.innerHTML = 'This message has been deleted';

    newMessageDiv.appendChild(mesInfo);
    newMessageDiv.appendChild(mesText);

    var chatPanel = window.document.getElementsByClassName('message-box')[0];

    chatPanel.replaceChild(newMessageDiv,click());

    var currentText = click().lastChild.innerHTML;
    var index = findMessageIndex(currentText);
    deleteMessageLS(index);
}

function changeUserActivity() {

    var usersOnlineList = window.document.getElementsByClassName('users-online')[0];
    var newUserOnline = document.createElement('li');

    newUserOnline.className = 'user-online';
    newUserOnline.innerHTML = getUserName();

    usersOnlineList.appendChild(newUserOnline);
}

function CreateFile()
{
    var fso, f1;
    fso = new ActiveXObject("Scripting.FileSystemObject");
    f1 = fso.CreateTextFile("c:\\testfile.txt", true);
}

function uniqueId() {
    var date = Date.now();
    var random = Math.random() * Math.random();
    return Math.floor(date * random);
}

function findMessageIndex(messageText) {
    if (typeof(Storage) == undefined)  {
        return;
    }

    for(var i = 0; i < messages.length; i++) {
        if (messageText == messages[i].text) {
            return i;
        }
    }
}

function deleteMessageLS(mesIndex) {
    var messagesListJS = localStorage.getItem("messages");

    if (messagesListJS == null) {
        alert("localStorage is empty");
        return;
    }

    var messageList = JSON.parse(messagesListJS);

    messageList[mesIndex].text = "This message has been deleted";

    localStorage.setItem("messages", JSON.stringify(messageList));
}

function editMessageLS(mesIndex, newText) {
    var messagesListJS = localStorage.getItem("messages");

    if (messagesListJS == null) {
        alert("localStorage is empty");
        return;
    }

    var messageList = JSON.parse(messagesListJS);

    messageList[mesIndex].text = newText;

    localStorage.setItem("messages", JSON.stringify(messageList));
}

function addMessageOnUI(message) {

    var newMessageDiv = document.createElement('div');
    newMessageDiv.className = 'message';

    var messageInfoDiv = document.createElement('div');
    messageInfoDiv.className = 'my-message-info';
    messageInfoDiv.innerHTML = message.author + " " + message.date + ' <a href = "#" class ="change-mes" title = "Edit message" onclick="editMessage()"><img src = "http://s1.iconbird.com/ico/2013/3/636/w80h8013939672873.png" width=20em height=20em></a>' +
        '<a href = "#" class ="delete-mes" title = "Delete message" onclick="deleteMessage()"><img src = "http://www.iconsearch.ru/uploads/icons/49handdrawing/128x128/bin-full.png" width=20em height=20em></a>';


    var messageTextDiv = document.createElement('div');
    messageTextDiv.className = 'my-message-text';
    messageTextDiv.innerHTML = message.text;

    newMessageDiv.appendChild(messageInfoDiv);
    newMessageDiv.appendChild(messageTextDiv);

    var chatPanel = window.document.getElementsByClassName('message-box')[0];
    chatPanel.appendChild(newMessageDiv);

}

function loadMesScreen() {

    var j = 0;

    var authMes = [];

    var loadedMessages = JSON.parse(localStorage.getItem("messages"));

    if (typeof(Storage) == undefined)  {
        return;
    }

    for(var i = 0; i < loadedMessages.length; i++) {
        if (getUserName() == loadedMessages[i].author) {
            authMes[j] = loadedMessages[i];
            j++;
        }
    }

    for (var k = 0; k < authMes.length; k++) {
        addMessageOnUI(authMes[k]);
    }

}

function loadLastUser() {

    var usersOnlineList = window.document.getElementsByClassName('users-online')[0];
    var newUserOnline = document.createElement('li');

    newUserOnline.className = 'user-online';
    newUserOnline.innerHTML = getUserName();

    usersOnlineList.appendChild(newUserOnline);

    document.getElementById("username").innerHTML = "You logged in as : " + getUserName();

}

function getUserName() {
    return localStorage.getItem("userName");
}
