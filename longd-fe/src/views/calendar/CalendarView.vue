<template>
  <div class="demo-app">
    <div class="demo-app-sidebar">
      <div class="demo-app-sidebar-section">
        <h2>All Events ({{ currentEvents.length }})</h2>
        <ul>
          <li v-for="event in currentEvents" :key="event.id">
            <b>{{ event.startStr }}</b>
            <i>{{ event.title }}</i>
          </li>
        </ul>
      </div>
    </div>
    <div class="demo-app-main">
      <FullCalendar class="demo-app-calendar" :options="calendarOptions">
        <template v-slot:eventContent="arg">
          <b>{{ arg.timeText }}</b>
          <i>{{ arg.event.title }}</i>
        </template>
      </FullCalendar>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';

import {
  setCalendarInfo,
  updateCalendarInfo,
  getCalendarInfo,
  delCalendarInfo,
} from '@/utils/api/calendar';

// import { INITIAL_EVENTS, createEventId } from '@/utils/event-utils';
import Swal from 'sweetalert2';

const titleAlert = async () => {
  const { value: title } = await Swal.fire({
    title: '당신의 추억을 입력해주세요.',
    input: 'text',
    inputLabel: '당신의 추억 제목',
    inputPlaceholder: '추억을 입력해주세요.',
    showCancelButton: true,
  });
  if (title) {
    const { value: color } = await Swal.fire({
      title: 'Select color',
      input: 'select',
      inputOptions: {
        default: 'default',
        red: 'red',
        orange: 'orange',
        yellow: 'yellow',
        green: 'green',
        blue: 'blue',
        indigo: 'indigo',
        violet: 'violet',
        purple: 'purple',
        pink: 'pink',
        brown: 'brown',
        black: 'black',
      },
      inputPlaceholder: 'Select a color',
      showCancelButton: true,
    });
    if (color) {
      Swal.fire('Saved!', '', 'success');
    }
    return { title, color };
  }
};

// 추억 작성 메소드
const handleDateSelect = selectInfo => {
  titleAlert().then(res => {
    let title = res.title;
    let color = res.color;
    let calendarApi = selectInfo.view.calendar;
    calendarApi.unselect(); // clear date selection
    if (title && color) {
      calendarApi.addEvent({
        title,
        color,
        start: selectInfo.startStr,
        end: selectInfo.endStr,
      });
    }
  });
};

// 내가 이벤트를 클릭했을 시
const handleEventClick = clickInfo => {
  if (clickInfo.event) {
    console.log(clickInfo.event.id);
    contentAlert(clickInfo);
  }
};

// 클릭된 이벤트 정보 저장 변수
let currentClickInfo = null;
// let currentClickInfoId = null;

const getContentById = function (dataArray, findId) {
  for (const key in dataArray) {
    if (dataArray[key].id == findId) {
      return dataArray[key].content;
    }
  }
  return '';
};

const contentAlert = async clickInfo => {
  currentClickInfo = clickInfo;
  // currentClickInfoId = currentClickInfo.event.id;

  console.log('확인');
  console.log(dateList.value);
  console.log(clickInfo.event.id);

  const content = getContentById(dateList.value, clickInfo.event.id);

  console.log(content);

  let isSave = false;
  const inputValue = content;
  const result = await Swal.fire({
    title: '추억내용',
    input: 'textarea',
    inputLabel: '',
    inputPlaceholder: '당신의 추억내용을 써주세요.',
    inputAttributes: {
      'aria-label': 'Type your message here',
    },
    inputValue,
    showDenyButton: true,
    showCancelButton: true,
    confirmButtonText: '저장',
    denyButtonText: '삭제',
    cancelButtonText: '취소',
  });

  if (result.isConfirmed) {
    isSave = true;
    Swal.fire('Saved!', '', 'success');
  } else if (result.isDenied) {
    Swal.fire('삭제되었습니다.', '', 'error');
    confirmDeleteEvent();
  }

  if (isSave && result.value) {
    console.log('저장-------');
    console.log(clickInfo);
    console.log(result.value);
    date.id = clickInfo.event.id;
    date.title = clickInfo.event._def.title;
    date.content = result.value;
    date.start = clickInfo.event.startStr;
    date.end = clickInfo.event.endStr;
    date.color = clickInfo.event.backgroundColor;
    changeCalendar(date.id, date);
  }
};

// 이벤트 삭제 관련 메소드
const confirmDeleteEvent = () => {
  if (
    currentClickInfo &&
    confirm(`정말로 '${currentClickInfo.event.title}'를 삭제하시겠습니까?`)
  ) {
    currentClickInfo.event.remove();
  }
};

const handleEvents = events => {
  currentEvents.value = events;
};

let dateList = ref([]);

const getCalendar = async () => {
  try {
    console.log(1);
    const res = await getCalendarInfo(1);
    dateList.value = res.data;
    console.log('조회');
    console.dir(dateList);
  } catch (err) {
    console.log(err);
  }
};

const saveCalendarTitle = async () => {
  try {
    console.log(date);
    await setCalendarInfo({
      ...date,
    });
    await getCalendar();
  } catch (err) {
    console.log(err);
  }
};

const changeCalendar = async () => {
  try {
    console.log('수정');
    console.log(date.id);
    await updateCalendarInfo(date.id, {
      ...date,
    });
    await getCalendar();
  } catch (err) {
    console.log(err);
  }
};
const deleteCalendar = async () => {
  try {
    console.log('삭제');
    console.log(date.id);
    console.log(date);
    await delCalendarInfo(date.id);
  } catch (err) {
    console.log(err);
  }
};

const date = {
  coupleListId: 1,
  id: 0,
  title: '',
  start: '',
  end: '',
  color: '',
};

const currentEvents = ref([]);

// Variables
const calendarOptions = ref({
  plugins: [
    dayGridPlugin,
    interactionPlugin, // needed for dateClick
  ],
  initialView: 'dayGridMonth',
  // initialEvents: INITIAL_EVENTS,
  // alternatively, use the `events` setting to fetch from a feed, 이걸로 저장하는 느낌을 줄 수 있을 듯
  customButtons: {
    saveButton: {
      text: '저장하기',
      click: function () {
        alert('clicked the custom button!');
      },
    },
  },
  headerToolbar: {
    right: 'prev,next today saveButton',
  },
  editable: true,
  selectable: true,
  selectMirror: true,
  select: handleDateSelect,
  eventClick: handleEventClick,
  eventsSet: handleEvents, // 이게 있어야 이벤트를 화면에 띄울 수 있음
  eventAdd: function (obj) {
    // 이벤트가 추가되면 발생하는 이벤트
    date.title = obj.event._def.title;
    date.start = obj.event.startStr;
    date.end = obj.event.endStr;
    // console.log('------------------');
    // console.log(obj.event.backgroundColor);
    date.color = obj.event.backgroundColor;
    // console.log(date);
    saveCalendarTitle(date);
  },
  eventChange: function (obj) {
    // 이벤트가 수정되면 발생하는 이벤트
    date.id = obj.event.id;
    date.title = obj.event._def.title;
    date.start = obj.event.startStr;
    date.end = obj.event.endStr;
    date.color = obj.event.backgroundColor;
    console.log('-----------');
    console.log(obj);
    // DB에서 id만 받을 수 있으면 끝인데
    // 조회 먼저 해보자
    console.log(date);
    changeCalendar(date.id, date);
  },
  eventRemove: function (obj) {
    // 이벤트가 삭제되면 발생하는 이벤트
    date.id = obj.event.id;
    // date.title = obj.event._def.title;
    // date.start = obj.event.startStr;
    // date.end = obj.event.endStr;
    // date.color = obj.event.backgroundColor;
    // console.log(date);
    deleteCalendar(date.id);
  },
  events: dateList,
});

onMounted(() => {
  getCalendar();
});
</script>

<style lang="css">
h2 {
  margin: 0;
  font-size: 16px;
}

ul {
  margin: 0;
  padding: 0 0 0 1.5em;
}

li {
  margin: 1.5em 0;
  padding: 0;
}

b {
  /* used for event dates/times */
  margin-right: 3px;
}

.demo-app {
  display: flex;
  min-height: 100%;
  font-family:
    Arial,
    Helvetica Neue,
    Helvetica,
    sans-serif;
  font-size: 14px;
}

.demo-app-sidebar {
  width: 300px;
  line-height: 1.5;
  background: #eaf9ff;
  border-right: 1px solid #d3e2e8;
}

.demo-app-sidebar-section {
  padding: 2em;
}

.demo-app-main {
  flex-grow: 1;
  padding: 3em;
}

.fc {
  /* the calendar root */
  max-width: 1100px;
  margin: 0 auto;
}
</style>
