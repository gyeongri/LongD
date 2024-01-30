<template>
  <div>
    <div id="mydraggable">Drag me!</div>
    <FullCalendar :options="calendarOptions" />
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin, { Draggable } from '@fullcalendar/interaction';

const handleDateClick = arg => {
  console.log(arg);
  alert('date click! ' + arg.dateStr);
};

const handleEventClick = clickInfo => {
  if (
    confirm(
      `Are you sure you want to delete the event '${clickInfo.event.title}'`,
    )
  ) {
    clickInfo.event.remove();
  }
};

const calendarOptions = {
  plugins: [dayGridPlugin, interactionPlugin],
  initialView: 'dayGridMonth',
  dateClick: handleDateClick,
  eventClick: handleEventClick,
  editable: true, // 수정 가능?
  selectable: true,
  nowIndicator: true, // 현재 시간 마크
  dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
  events: [
    { title: 'event 1', date: '2024-01-01' },
    {
      title: 'event 2',
      start: '2024-01-07T10:30:00',
      end: '2024-01-09T11:30:00',
    },
  ],
};

onMounted(() => {
  const draggableEl = document.getElementById('mydraggable');
  new Draggable(draggableEl, {
    eventData: function (eventEl) {
      return {
        title: eventEl.innerText.trim(),
      };
    },
  });
});
</script>

<style scoped>
/* 추가 스타일링이 필요한 경우 여기에 추가하세요 */
#mydraggable {
  margin-top: 20px;
  padding: 10px;
  background-color: #ccc;
  cursor: move;
  width: 100px; /* 가로 길이 설정 */
}
</style>
