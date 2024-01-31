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
  <AppModal :open="isModalOpen" modalId="calendarId">
    <template v-slot:title>추억 내용을 등록하세요</template>
    <template v-slot:body>
      <div class="mb-4">{{ currentClickInfoId }}</div>
    </template>
    <template v-slot:footer>
      <button @click="closeModal" class="btn btn-outline btn-primary mx-2">
        취소
      </button>
      <button @click="confirmDeleteEvent" class="btn btn-outline btn-primary">
        삭제
      </button>
    </template>
  </AppModal>
</template>

<script setup>
import { ref } from 'vue';
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';

import { INITIAL_EVENTS, createEventId } from '@/utils/event-utils';
import AppModal from '@/components/app/AppModal.vue';

// 계획 작성 메소드
const handleDateSelect = selectInfo => {
  let title = prompt('새로운 추억을 입력해주세요.');
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
};

// 모달을 열고 닫는 상태 변수
const isModalOpen = ref(false);
// 클릭된 이벤트 정보 저장 변수
let currentClickInfo = null;
let currentClickInfoId = null;

// 모달을 열기 위한 메소드
// 현재 클릭된 이벤트 정보 저장도 해줌.
// 추가적으로 모달을 열 때 백으로 부터 해당 id의 정보도 불러오기(axios)
const openModal = clickInfo => {
  currentClickInfo = clickInfo;
  currentClickInfoId = currentClickInfo.event.id;
  isModalOpen.value = true;
};

// 모달을 닫기 위한 메소드
const closeModal = () => {
  isModalOpen.value = false;
};

// 이벤트 삭제 관련 메소드
const confirmDeleteEvent = () => {
  if (
    currentClickInfo &&
    confirm(`정말로 '${currentClickInfo.event.title}'를 삭제하시겠습니까?`)
  ) {
    currentClickInfo.event.remove();
  }
  closeModal(); // 모달 닫기
};

const handleEventClick = clickInfo => {
  console.log(clickInfo.event.id);
  // if (confirm(`정말로 '${clickInfo.event.title}'를 삭제하시겠습니까?`)) {
  //   clickInfo.event.remove();
  // }
  // 모달 열기
  openModal(clickInfo);
};

const handleEvents = events => {
  currentEvents.value = events;
  console.log(events);
  console.log(currentEvents.value);
};

// Variables
const calendarOptions = ref({
  plugins: [
    dayGridPlugin,
    interactionPlugin, // needed for dateClick
  ],
  initialView: 'dayGridMonth',
  initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed, 이걸로 저장하는 느낌을 줄 수 있을 듯

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
