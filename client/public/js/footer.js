function startDateTime() {
  let today = new Date();
  let monthNames = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December',
  ];

  let dayNames = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];

  // Break out today's date
  let day = today.getDate();
  let dayLong = dayNames[today.getDay()];
  let month = today.getMonth() + 1;
  let monthLong = monthNames[today.getMonth()];
  let year = today.getFullYear();

  let h = today.getHours();
  let am_pm = 'AM';
  if (h > 12) {
    am_pm = 'PM';
    h = h - 12;
  }

  let m = today.getMinutes();
  let s = today.getSeconds();

  // add a zero in front of numbers < 10
  m = checkTime(m);
  s = checkTime(s);

  if (
    typeof $('#modifyTs_').val() !== 'undefined' &&
    typeof $('#modifyUserId').val() !== 'undefined'
  ) {
    let modifyTs = $('#modifyTs_').val();
    let modifyUserId = $('#modifyUserId').val();
    document.getElementById('footerStatusMsg').innerHTML =
      ', this record was last modified by ' + modifyUserId + ' on ' + modifyTs;
  }

  // Update page with current date and time value
  document.getElementById('todays_date_time').innerHTML =
    dayLong +
    ', ' +
    monthLong +
    ' ' +
    day +
    ', ' +
    year +
    '    ' +
    h +
    ':' +
    m +
    ' ' +
    am_pm +
    '&nbsp;';
  t = setTimeout(function () {
    startDateTime();
  }, 30000);
}

function checkTime(i) {
  if (i < 10) {
    i = '0' + i;
  }
  return i;
}

$(document).ready(function () {
  startDateTime();
});
