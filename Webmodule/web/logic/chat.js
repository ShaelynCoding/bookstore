var socket = null;
var singleContent=undefined;
var singleHtml=undefined;
function listen() {
	var wsURI = window.location.protocol == 'http:'
		? 'ws://' + window.location.host + '/Webmodule_war_exploded/chat'
		: 'wss://' + window.location.host + '/Webmodule_war_exploded/chat';

	socket = new WebSocket(wsURI);
	socket.onopen = function() {
		//displayStatus('Open');
		//displayMessage("Connection is now open.","success");
		// $("#content").append("<kbd>Welcome!</kbd></br>");
		sendMsg(
			{
				action:"setUser",
				username:getUserName()
			}
		);
	};


	socket.onmessage = function(event) {
		var json=JSON.parse(event.data);
		switch (json.action)
		{
			case "users":
				updateUsers(json.users);
				break;
			case "chat":
				addChat(json);
				break;

		}
		//displayMessage("The response was received!" + event.data, "success");
	};
	socket.onclose = function() {
		//displayStatus('Closed');
		//displayMessage("The connection was closed or timed out.","error");
	};
	socket.onerror = function(event) {
		//displayMessage("Error!" + event.data, "error");
		alert("error");
	};
}

function sendMsg(object) {
	if(socket==null)
	{
		alert("can't connect");
		return;

	}
	socket.send(JSON.stringify(object));
}
function disconnect() {
	if(socket!=null)
	{
		socket.close();
		socket=null;
	}

}
function chat() {
	var text=$("#msg").val();
	if(text==undefined || text=="")
		return;
	sendMsg(
		{
			action:"chat",
			value:text
		}
	);


}
function updateUsers(users) {

}
function addChat(object) {
	if(singleContent==undefined)
	{
		singleContent=$("#content").html();
		$("#content").html("");
	}
	var contentDoc=$("#content");
	contentDoc.prepend(singleContent);
	var newChat=contentDoc.find(".ChatMsg");
	newChat.fadeIn("slow");
	newChat.find(".imgUser").html(object.user);
	newChat.find(".msgText").html(object.text);
}

document.onkeydown = function(event){
	var e = event || window.event || arguments.callee.caller.arguments[0];
	if(e && e.keyCode == 13){ // enter 键
		chat();
	}
}; 