let userId = document.getElementById("user_id").value;
let baseUrl ="http://localhost:8080"
//let baseUrl = "http://demo.codingnomads.co:8082/muttsapp";

//--------------------   CREATE CHAT BUBBLE   --------------------//

function createChatBubble(msgObj) {
  console.log(msgObj);
  let ChatBubble = document.createElement("div");
  console.log(msgObj.sender_id)
  console.log(user_id)
//  by adding the + symbol we make sure the values we are comparing are numbers and not strings
  if (+msgObj.sender_id === +userId) {
    ChatBubble.classList.add("chat-bubble", "out");
  } else {
    ChatBubble.classList.add("chat-bubble", "in");
  }
  let paragraph = document.createElement("p");
  paragraph.innerText = msgObj.message;

  ChatBubble.appendChild(paragraph);

  let wrapper = document.getElementById("chat-bubble-wrapper");
  wrapper.prepend(ChatBubble); //add messages to the beginning
}

//--------------------   CREATE CHAT BUBBLE(S)   --------------------//

function createChatBubbles(dataObj) {
  let chatsArr = dataObj.data;
  chatsArr.forEach((chatObj) => createChatBubble(chatObj));
}

//--------------------   CREATE MESSAGE PREVIEW BOX   --------------------//

function createMessagePreviewBox(chatObj) {
  console.log(chatObj);
  let MessagePreviewBox = document.createElement("div");
  MessagePreviewBox.classList.add("message-preview-box");
  MessagePreviewBox.setAttribute("data-chat_id", chatObj.chat_id);
  MessagePreviewBox.setAttribute("data-sender_id", chatObj.sender_id);
  MessagePreviewBox.addEventListener("click", previewBoxClick);

  let imgWrap = document.createElement("div");
  imgWrap.setAttribute("data-chat_id", chatObj.chat_id);
  imgWrap.setAttribute("data-sender_id", chatObj.sender_id);
  imgWrap.classList.add("img-wrap");
  let image = document.createElement("img");
  image.setAttribute("data-chat_id", chatObj.chat_id);
  image.setAttribute("data-sender_id", chatObj.sender_id);
  image.setAttribute("src", chatObj.photo_url);
  // image.style.backgroundImage = `url(${chatObj.photo_url})`
  image.setAttribute("alt", "default icon");

  imgWrap.appendChild(image);

  let textWrap = document.createElement("div");
  textWrap.setAttribute("data-chat_id", chatObj.chat_id);
  textWrap.setAttribute("data-sender_id", chatObj.sender_id);
  textWrap.classList.add("message-text-wrap");
  let nameParagraph = document.createElement("p");
  nameParagraph.setAttribute("data-chat_id", chatObj.chat_id);
  nameParagraph.setAttribute("data-sender_id", chatObj.sender_id);
  nameParagraph.innerText = chatObj.chat_name;
  let messageParagraph = document.createElement("p");
  messageParagraph.setAttribute("data-chat_id", chatObj.chat_id);
  messageParagraph.setAttribute("data-sender_id", chatObj.sender_id);
  messageParagraph.innerText = chatObj.last_message;

  textWrap.appendChild(nameParagraph);
  textWrap.appendChild(messageParagraph);

  let dateWrap = document.createElement("div");
  dateWrap.setAttribute("data-chat_id", chatObj.chat_id);
  dateWrap.setAttribute("data-sender_id", chatObj.sender_id);
  dateWrap.classList.add("date-wrap");
  let dateP = document.createElement("p");
  dateP.setAttribute("data-chat_id", chatObj.chat_id);
  dateP.setAttribute("data-sender_id", chatObj.sender_id);
  console.log(chatObj.date_sent);
  dateP.innerHTML = new Date(chatObj.date_sent).toLocaleDateString(); //always instantiate a new date

  dateWrap.appendChild(dateP);

  MessagePreviewBox.appendChild(imgWrap);
  MessagePreviewBox.appendChild(textWrap);
  MessagePreviewBox.appendChild(dateWrap);

  let MessagePreviewWrapper = document.getElementById(
    "message-preview-wrapper"
  );
  MessagePreviewWrapper.appendChild(MessagePreviewBox);
}


//--------------------   CREATE CONTACT PREVIEW BOX   --------------------//

function createContactPreviewBox() {
  let contactPreviewBox = document.createElement("div");
  contactPreviewBox.classList.add("contact-preview-box");

  let imgWrap = document.createElement("div");
  imgWrap.classList.add("img-wrap");
  let image = document.createElement("img");
  image.setAttribute("src", "./images/icons8-pikachu-pokemon-50.png");
  image.setAttribute("alt", "default icon");

  imgWrap.appendChild(image);

  let textWrap = document.createElement("div");
  textWrap.classList.add("contact-text-wrap");
  let nameParagraph = document.createElement("p");
  nameParagraph.innerText = "contact - user's - name";
  let messageParagraph = document.createElement("p");
  messageParagraph.innerText = "contact - last message";

  textWrap.appendChild(nameParagraph);
  textWrap.appendChild(messageParagraph);

  contactPreviewBox.appendChild(imgWrap);
  contactPreviewBox.appendChild(textWrap);

  let contactPreviewWrapper = document.getElementById(
    "contact-preview-wrapper"
  );
  contactPreviewWrapper.appendChild(contactPreviewBox);
}

createContactPreviewBox();

//--------------------   CREATE PREVIEW BOXE(S)  --------------------//

function createPreviewBoxes(dataObj) {
  let chatsArr = dataObj.data;
  chatsArr.forEach((chatObj) => createMessagePreviewBox(chatObj));
}

//--------------------   PREVIEW BOX CLICK  --------------------//

function previewBoxClick(event) {
  document.getElementById("chat-bubble-wrapper").innerHTML = " ";
  console.log(event.target);
  let chatID = event.target.dataset.chat_id;
  let senderID = event.target.dataset.sender_id;

  document.getElementById("send-message").dataset.chat_id = chatID; //getting message form data attribute and setting to chatID

  fetch(`${baseUrl}/users/${userId}/chats/${senderID}`)
    .then((response) => response.json())
    .then((dataObj) => createChatBubbles(dataObj));
}

//--------------------   ADD EVENT(SUBMIT) LISTENER    --------------------//

let newMessageForm = document.getElementById("send-message");

newMessageForm.addEventListener("submit", function (event) {
  event.preventDefault(); //
  console.log(event);

  let msg = document.getElementById("new-message").value;

  let msgObj = {
    message: msg,
    sender_id: userId,
    chat_id: event.target.dataset.chat_id, //parsing into the event object target > dataset > chat_id

    // chat_id: document.getElementById('send-message').dataset.chat_id
  };

  createChatBubble(msgObj);
  sendMessageToAPI(msgObj);

  document.getElementById("new-message").value = " ";
});

//---------------------GET MODAL ELEMENT------------------------

//---get modal element
let modal = document.getElementById("popup-modal-window");

//---get open modal button
let newChatModalButton = document.getElementById("modal-button");
//--open icon profile modal button
let iconProfileModalButton = document.getElementById(
  "icon-profile-modal-button"
);
//---open new group modal button
let newGroupModalButton = document.getElementById("newgroup-modal-button");
// ---open profile modal button
let profileModalButton = document.getElementById("profile-modal-button");
// ---open setting modal button
let settingModalButton = document.getElementById("setting-modal-button");

//---get close button
let closeButton = document.getElementsByClassName("close-button")[0];

//---listen for OPEN click
newChatModalButton.addEventListener("click", openNewChatModal);
newGroupModalButton.addEventListener("click", openNewGroupModal);
profileModalButton.addEventListener("click", openProfileModal);
settingModalButton.addEventListener("click", openSettingsModal);
iconProfileModalButton.addEventListener("click", openIconProfileModal);

//------------CREATE SEARCH BOX---------------
function createSearchBox() {
  let searchFormWrap = document.createElement("div");
  searchFormWrap.classList.add("search");
  searchFormWrap.classList.add("form-wrap");
  searchFormWrap.setAttribute("id", "search-form-wrap");

  let searchForm = document.createElement("form");
  searchForm.setAttribute("id", "search-contact-form");
  searchForm.innerHTML =
    '<i class="static - search - icon 	fa fa - search"></i>';

  let searchInput = document.createElement("input");
  searchInput.setAttribute("type", "text");
  searchInput.setAttribute("name", "search");
  searchInput.setAttribute("id", "search-contact-input");
  searchInput.setAttribute("placeholder", "Search...");

  searchForm.append(searchInput);
  searchFormWrap.appendChild(searchForm);

  let modalContent = document.getElementById("modal-content-id");
  modalContent.appendChild(searchFormWrap);
}

//--------------------   CREATE CONTACT PREVIEW BOX   --------------------//

function createContactPreviewBox() {
  let contactPreviewWrapper = document.createElement("div");
  contactPreviewWrapper.classList.add("contact-preview-wrap");
  contactPreviewWrapper.setAttribute("id", "contact-preview-wrapper");

  let contactPreviewBox = document.createElement("div");
  contactPreviewBox.classList.add("contact-preview-box");

  let imgWrap = document.createElement("div");
  imgWrap.classList.add("img-wrap");
  let image = document.createElement("img");
  image.setAttribute("src", "./images/icons8-pikachu-pokemon-50.png");
  image.setAttribute("alt", "default icon");

  imgWrap.appendChild(image);

  let textWrap = document.createElement("div");
  textWrap.classList.add("contact-text-wrap");
  let nameParagraph = document.createElement("p");
  nameParagraph.innerText = "contact - user's - name";
  let messageParagraph = document.createElement("p");
  messageParagraph.innerText = "contact - last message";

  contactPreviewWrapper.appendChild(contactPreviewBox);

  contactPreviewBox.appendChild(imgWrap);
  contactPreviewBox.appendChild(textWrap);

  textWrap.appendChild(nameParagraph);
  textWrap.appendChild(messageParagraph);

  let modalContent = document.getElementById("modal-content-id");
  modalContent.appendChild(contactPreviewWrapper);
}

// --------------CREATE FLOATING SEND BUTTON----------
function createFloatingSendButton() {
  let floatButton = document.createElement("div");
  floatButton.classList.add("float-button");
  floatButton.setAttribute("id", "float-button-id");
  floatButton.innerHTML = '<i class="float-angle-icon fa fa-arrow-right"></i>';

  let modalContent = document.getElementById("modal-content-id");
  modalContent.append(floatButton);
}

function openNewChatModal() {
  let modalContent = document.getElementById("modal-content-id");
  modalContent.innerHTML = "";
  //need to grab elementbyid in order to set innerHTML
  let hTwoInnerText = document.getElementById("hTwo-Inner-Text");
  hTwoInnerText.innerHTML = "Select Contact";

  let modalHeaderIcon = document.getElementById("modal-header-icon");
  modalHeaderIcon.innerHTML = "";

  let button = document.createElement("button");
  button.innerHTML = '<i class="close-button 	fa fa-user-plus"></i>';

  modalHeaderIcon.appendChild(button);

  createSearchBox();
  createContactPreviewBox();
  createFloatingSendButton();
  openModal();
}

function openNewGroupModal() {
  //need to grab elementbyid in order to set innerHTML
  let hTwoInnerText = document.getElementById("hTwo-Inner-Text");
  hTwoInnerText.innerHTML = "Add group participants";

  let modalHeaderIcon = document.getElementById("modal-header-icon");
  modalHeaderIcon.innerHTML = "";

  openModal();
}

function openProfileModal() {
  let hTwoInnerText = document.getElementById("hTwo-Inner-Text");
  hTwoInnerText.innerHTML = "Profile";

  let modalHeaderIcon = document.getElementById("modal-header-icon");
  modalHeaderIcon.innerHTML = "";

  openModal();
}

function openSettingsModal() {
  let hTwoInnerText = document.getElementById("hTwo-Inner-Text");
  hTwoInnerText.innerHTML = "Settings";

  let modalHeaderIcon = document.getElementById("modal-header-icon");
  modalHeaderIcon.innerHTML = "";

  openModal();
}

function openIconProfileModal() {
  let hTwoInnerText = document.getElementById("hTwo-Inner-Text");
  hTwoInnerText.innerHTML = "Profile";

  let modalHeaderIcon = document.getElementById("modal-header-icon");
  modalHeaderIcon.innerHTML = "";

  let modalContent = document.getElementsByClassName("modal-content");
  modalContent.innerHTML = "";

  openModal();
}

//---listen for CLOSE click
closeButton.addEventListener("click", closeModal);

//---Listen for OUTSIDE click
window.addEventListener("click", outsideClick);

//---Function to open modal
function openModal() {
  modal.style.display = "block";
  document.getElementById("header-main").style.opacity = 0.2;
  document.querySelector(".main-content").style.opacity = 0.2;
}

//---Function to close modal
function closeModal() {
  modal.style.display = "none";
  document.getElementById("header-main").style.opacity = 1;
  document.querySelector(".main-content").style.opacity = 1;
}

//Function to close modal if outside click
function outsideClick(event) {
  if (event.target == modal) {
    modal.style.display = "none";
    document.getElementById("header-main").style.opacity = 1;
    document.querySelector(".main-content").style.opacity = 1;
  }
}

// ------------------ON CLICK DROP DOWN MENU (SIDE BAR)------------------

let dropDownButton = document.getElementById("dropdown-button");

let dropdownContent = document.getElementById("dropdown-content-id");

// ------click to open menu
dropDownButton.addEventListener("click", openDropdown);

//--------function to open menu
function openDropdown(event) {
  let el = document.getElementById("dropdown-content-id");
  console.log(el.classList);
  document.getElementById("dropdown-content-id").classList.toggle("show");
  console.log(el.classList);
}

//---Listen for OUTSIDE click
//---this works for both sidebar and header main dropdown menu
window.addEventListener("click", outsideClickButton);

function outsideClickButton(e) {
  console.log(e.target.closest(".dropdown-button"));
  if (!e.target.closest(".dropdown-button")) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdownOne = dropdowns[i];
      if (openDropdownOne.classList.contains("show")) {
        openDropdownOne.classList.remove("show");
      }
    }
  }
}
// ------------------ON CLICK DROP DOWN MENU (HEADER MAIN)------------------

let dropDownButton2 = document.getElementById("dropdown-button-main");

// ------click to open menu
dropDownButton2.addEventListener("click", openDropdown2);

//--------function to open menu
function openDropdown2(event) {
  document.getElementById("dropdown-content-main").classList.toggle("show");
}

//--------------------   SEND MESSAGE TO API   --------------------//

function sendMessageToAPI(msgObj) {
  let postParams = {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
    },
    body: JSON.stringify(msgObj),
  };
  fetch(`${baseUrl}/users/${userId}/message`, postParams)
    .then((res) => res.json())
    .then((res) => getUserChats());
}

//--------------------   GET USER CHATS    --------------------//

function getUserChats() {
  document.getElementById("message-preview-wrapper").innerHTML = ""; //clears previous message before fetching for new one(s)

  fetch(`${baseUrl}/users/${userId}/chats/`)
    .then((Response) => Response.json())
    .then((dataObj) => createPreviewBoxes(dataObj));

  // .then(function(dataObj){
  //   createPreviewBoxes(dataObj);
  // })
}

getUserChats();

//--------------------   NEW USER    --------------------//

function newUser() {
  let postData = {
    first_name: "",
    last_name: "",
    username: "",
    photo_url: "",
    date_sent: "",
  };
  let postParams = {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
    },
    body: JSON.stringify(postData),
  };
  fetch(`${baseUrl}/users/`, postParams)
    .then((res) => res.json())
    .then((res) => console.log(res));
}


function makeNewChatForm(e) {
  newChatModalBody.innerHTML = "Loading Chat Form";
  fetch(`${baseUrl}/users/`)
    .then(res => res.json())
    .then(data => {
      console.log(data);
      let usersArray = data.data;
      let frm = document.createElement('form');
      frm.id = `new-chat-frm`;
      let formString = ``;
      formString += `<input id="new-chat-user" type="text" list="users-list" class="form-control">`;
      formString += `<datalist id="users-list">`
      usersArray.forEach(userObj => {
        formString += `<option data-value="${userObj.id}" value="${userObj.first_name} ${userObj.last_name}"></option> `
      })
      formString += `</datalist>`
      formString += `<input type="submit" class="btn btn-success">`
      frm.innerHTML = formString;
      frm.addEventListener('submit', newChatSubmit)
      newChatModalBody.innerHTML = "";
      newChatModalBody.appendChild(frm);
    })
}
function newChatSubmit(e) {
  e.preventDefault()
  let options = document.getElementById('users-list').options;
  console.log(document.getElementById('users-list').options)
  console.log(e.target.elements)
  let val = e.target.elements["new-chat-user"].value
  console.log(val)
  let newChatUserId;
  Array.from(options).forEach(option => {
    if (option.value === val) {
      newChatUserId = option.getAttribute('data-value');
    }
  })
  console.log(newChatUserId)
  // Write submit fetch here
}

//------------------ EMOJI PLUGGIN ----------------
window.addEventListener("DOMContentLoaded", () => {
  const button = document.querySelector("#emoji-btn");
  const picker = new EmojiButton();
  picker.on("emoji", emoji => {
    document.querySelector("#new-message").value += emoji;
  });
  button.addEventListener("click", () => {
    picker.pickerVisible ? picker.hidePicker() : picker.showPicker(button);
  });
});

