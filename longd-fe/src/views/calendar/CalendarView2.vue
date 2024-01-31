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
import { ref } from 'vue';
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
<<<<<<< HEAD:longd-fe/src/views/calendar/CalendarView2.vue

import { INITIAL_EVENTS, createEventId } from '@/utils/event-utils';
import AppModal from '@/components/app/AppModal.vue';
=======
>>>>>>> geunryeol:전근렬/longd-fe/src/views/calendar/CalendarView2.vue

import { INITIAL_EVENTS, createEventId } from '@/utils/event-utils';

import Swal from 'sweetalert2';

const titleAlert = async () => {
  const { value: title } = await Swal.fire({
    title: '당신의 추억을 입력해주세요.',
    input: 'text',
    inputLabel: '당신의 추억 제목',
    inputPlaceholder: '추억을 입력해주세요.',
  });
  if (title) {
    Swal.fire('Saved!', '', 'success');
    return title;
  }
};

// 추억 작성 메소드
const handleDateSelect = selectInfo => {
  titleAlert().then(res => {
    let title = res;
    let calendarApi = selectInfo.view.calendar;
    calendarApi.unselect(); // clear date selection
    if (title) {
      calendarApi.addEvent({
        id: createEventId(),
        title,
        start: selectInfo.startStr,
        end: selectInfo.endStr,
        allDay: selectInfo.allDay,
      });
    }
  });
};

// 내가 이벤트를 클릭했을 시
const handleEventClick = clickInfo => {
  console.log(clickInfo.event.id);
  contentAlert(clickInfo);
};

// 클릭된 이벤트 정보 저장 변수
let currentClickInfo = null;
// let currentClickInfoId = null;

const contentAlert = async clickInfo => {
  currentClickInfo = clickInfo;
  // currentClickInfoId = currentClickInfo.event.id;
  let isSave = false;
  const result = await Swal.fire({
    title: '추억내용',
    input: 'textarea',
    inputLabel: '',
    inputPlaceholder: '당신의 추억내용을 써주세요.',
    inputAttributes: {
      'aria-label': 'Type your message here',
    },
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
    console.log(result.value);
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

// Variables
const calendarOptions = ref({
  plugins: [
    dayGridPlugin,
    interactionPlugin, // needed for dateClick
  ],
  initialView: 'dayGridMonth',
  initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed, 이걸로 저장하는 느낌을 줄 수 있을 듯
<<<<<<< HEAD:longd-fe/src/views/calendar/CalendarView2.vue

=======
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
>>>>>>> geunryeol:전근렬/longd-fe/src/views/calendar/CalendarView2.vue
  editable: true,
  selectable: true,
  selectMirror: true,
  dayMaxEvents: true,
  select: handleDateSelect,
  eventClick: handleEventClick,
  eventsSet: handleEvents, // 이게 있어야 이벤트를 화면에 띄울 수 있음
  /* you can update a remote database when these fire:
  eventAdd:
  eventChange:
  eventRemove:
  */
});

const currentEvents = ref([]);
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
