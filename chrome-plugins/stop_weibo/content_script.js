// var setting = setting || {
//   break_time: 5,
//   study_time: 5,
//   // margin_left: 20,  // in px
//   // margin_top: 20,   // in px
// };

// var g_counter = g_counter || 1;
// var g_first = g_first || true;
// var g_noti_block = g_noti_block || undefined;
// var g_timer_text = g_timer_text || undefined;

function mask (setting) {
  console.log("hi, I'm in content_script.js...");

  // g_noti_block = get_or_create_noti_block();
  var my_id = "jieweibo-nonono";
  var noti_block = document.getElementById(my_id);
  if (noti_block) {
    console.log("node exists, ignore");
    return;
  }

  noti_block = create_container();
  noti_block.id = my_id;

  var title = document.createElement('h2');
  title.textContent = "STOP!";
  noti_block.appendChild(title);

  var timer_text = document.createElement('p');
  timer_text.textContent = '' + (setting["study_time"]);
  noti_block.appendChild(timer_text);

  noti_block.style.visibility = "hidden";

  var body = document.getElementsByTagName("body")[0];
  body.appendChild(noti_block);

  var param = {
    counter: 0,
    first: true,
    noti_block: noti_block,
    timer_text: timer_text,
    break_time: setting.break_time,
    study_time: setting.study_time,
  };

  // setTimeout(on_timeout, 1000, param);
  on_timeout(param);
}

function on_timeout (param) {
  param.counter = param.counter + 1;
  if (param.counter <= param.break_time) {
    console.log("chi...");
    // return;
  } else if (param.counter <= (param.break_time + param.study_time)) {
    param.timer_text.textContent = '' + (param.counter - param.break_time);
    if (!param.first) {
      console.log("not first time");
    } else {
      param.noti_block.style.visibility = "visible";
      param.first = false;
    }
  } else {
    param.counter = 0;
    param.first = true;
    param.noti_block.style.visibility = "hidden";
  }

  setTimeout(on_timeout, 1000, param);
}

function create_container () {
  var e = document.createElement("div");
  // var width = document.body.clientWidth - (2*SETTING["margin_left"]);
  // var height = document.body.clientHeight - (2*SETTING["margin_top"]);
  e.style.width = '95%';
  e.style.height = '95%';
  e.style.marginLeft = "auto";
  e.style.marginRight = "auto";
  e.style.border = "1px solid red";
  e.style.position = "fixed";
  e.style.left = "20px";
  e.style.top = "20px";
  e.style.backgroundColor = "#FFFFFF";
  e.style.textAlign = "center";
  return e;
}

chrome.runtime.onMessage.addListener(function(setting, sender, sendResponse) {
  console.log("onMessage()");
  mask(setting);
});

