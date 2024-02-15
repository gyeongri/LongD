<template>
  <div class="demo-app">
    <!-- <div class="demo-app-sidebar rounded-xl">
      <div class="demo-app-sidebar-section">
        <h2>All Events ({{ currentEvents.length }})</h2>
        <ul>
          <li v-for="event in currentEvents" :key="event.id">
            <b>{{ event.startStr }}</b>
            <i>{{ event.title }}</i>
          </li>
        </ul>
      </div>
    </div> -->
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
import { useUserStore } from '@/stores/user';
const userStore = useUserStore();
const coupleId = ref('');
// 추억 작성 및 색깔 선정 알림창
const titleAlert = async () => {
  const { value: title } = await Swal.fire({
    title: '당신의 추억을 입력해주세요.',
    input: 'text',
    inputLabel: '',
    inputPlaceholder: '추억을 입력해주세요.',
    showCancelButton: true,
    confirmButtonColor: '#FF9CBD',
    cancelButtonColor: '#a0a0a0',
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
      confirmButtonColor: '#FF9CBD',
      cancelButtonColor: '#a0a0a0',
    });
    if (color) {
      Swal.fire('Saved!', '', 'success');
    }
    return { title, color };
  }
};

// 추억 작성 메소드 발동 (캘린더의 Date 클릭시)
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
    contentAlert(clickInfo);
  }
};

// 클릭된 이벤트 정보 저장 변수
let currentClickInfo = null;
// let currentClickInfoId = null;

// id를 통한 현재 내용 찾기
const getContentById = function (dataArray, findId) {
  for (const key in dataArray) {
    if (dataArray[key].id == findId) {
      return dataArray[key].content;
    }
  }
  return '';
};

// 추억 내용 알림창
const contentAlert = async clickInfo => {
  currentClickInfo = clickInfo;
  // currentClickInfoId = currentClickInfo.event.id;

  const content = getContentById(dateList.value, clickInfo.event.id);

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
    confirmButtonText: 'save',
    denyButtonText: 'delete',
    cancelButtonText: 'cancel',
    confirmButtonColor: '#FF9CBD',
    denyButtonColor: '#DC143C',
    cancelButtonColor: '#a0a0a0',
  });

  if (result.isConfirmed) {
    isSave = true;
    Swal.fire('Saved!', '', 'success');
  } else if (result.isDenied) {
    Swal.fire('삭제되었습니다.', '', 'error');
    confirmDeleteEvent();
  }

  if (isSave && result.value) {
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

// 현재 이벤트들 파악(사실 없어도 되긴 할듯)
const handleEvents = events => {
  currentEvents.value = events;
};

// 이벤트들 리스트
let dateList = ref([]);

// DB로 부터 이벤트들 조회
const getCalendar = async () => {
  try {
    const res = await getCalendarInfo(coupleId.value);
    dateList.value = res.data;
    console.dir(dateList);
  } catch (err) {
    console.log(err);
  }
};

// DB 생성
const saveCalendarTitle = async () => {
  try {
    await setCalendarInfo({
      ...date,
    });
    await getCalendar();
  } catch (err) {
    console.log(err);
  }
};

// DB 수정
const changeCalendar = async () => {
  try {
    await updateCalendarInfo(date.id, {
      ...date,
    });
    await getCalendar();
  } catch (err) {
    console.log(err);
  }
};

// DB 삭제
const deleteCalendar = async () => {
  try {
    await delCalendarInfo(date.id);
  } catch (err) {
    console.log(err);
  }
};

// 이벤트
const date = {
  id: 0,
  title: '',
  start: '',
  end: '',
  color: '',
};

// 현재 이벤트들 (없어도 되긴할듯)
const currentEvents = ref([]);

// 캘린더 옵션
const calendarOptions = ref({
  plugins: [
    dayGridPlugin,
    interactionPlugin, // needed for dateClick
  ],
  initialView: 'dayGridMonth',
  // initialEvents: INITIAL_EVENTS,
  // alternatively, use the `events` setting to fetch from a feed, 이걸로 저장하는 느낌을 줄 수 있을 듯
  customButtons: {
    // saveButton: {
    //   text: '저장하기',
    //   click: function () {
    //     alert('clicked the custom button!');
    //   },
    // },
  },
  headerToolbar: {
    right: 'prev,next today',
  },
  editable: true,
  selectable: true,
  selectMirror: true,
  select: handleDateSelect,
  eventClick: handleEventClick,
  // hover 들어갈 때
  eventMouseEnter: function (mouseEnterInfo) {
    let popContent = getContentById(
      dateList.value,
      mouseEnterInfo.event._def.publicId,
    );
    if (!popContent) {
      popContent = '아직 내용이 없습니다.';
    }
    // 현재 아이디 찾고, 그거로 content 쏴주기
    // 툴팁 요소 생성 또는 선택
    let tooltip = document.getElementById('event-tooltip');
    if (!tooltip) {
      tooltip = document.createElement('div');
      tooltip.id = 'event-tooltip';
      tooltip.className = 'tooltip';
      document.body.appendChild(tooltip);
    }
    tooltip.style.display = 'block';
    tooltip.style.left = mouseEnterInfo.jsEvent.pageX + 'px';
    tooltip.style.top = mouseEnterInfo.jsEvent.pageY + 'px';
    tooltip.innerText = popContent;
  },
  // hover 땔 때
  eventMouseLeave: function (mouseLeaveInfo) {
    const tooltip = document.getElementById('event-tooltip');
    if (tooltip) {
      tooltip.style.display = 'none'; // 마우스가 떠나면 툴팁을 숨깁니다.
    }
  },
  eventsSet: handleEvents, // 이게 있어야 이벤트를 화면에 띄울 수 있음
  eventAdd: function (obj) {
    // 이벤트가 추가되면 발생하는 이벤트
    date.title = obj.event._def.title;
    date.start = obj.event.startStr;
    date.end = obj.event.endStr;
    date.color = obj.event.backgroundColor;
    saveCalendarTitle(date);
  },
  eventChange: function (obj) {
    // 이벤트가 수정되면 발생하는 이벤트
    date.id = obj.event.id;
    date.title = obj.event._def.title;
    date.start = obj.event.startStr;
    date.end = obj.event.endStr;
    date.color = obj.event.backgroundColor;
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
    deleteCalendar(date.id);
  },
  events: dateList,
});

onMounted(() => {
  coupleId.value = userStore.getUserState?.coupleListId;
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
.tooltip {
  position: absolute;
  background-color: rgb(250, 168, 182);
  color: white;
  padding: 5px 10px;
  border-radius: 4px;
  font-size: 12px;
  z-index: 10000; /* 충분히 높은 값으로 설정하여 다른 요소들 위에 표시되도록 합니다. */
  display: none; /* 기본적으로 툴팁을 숨깁니다. */
  max-width: 200px; /* 원하는 최대 가로 길이를 지정합니다. */
  max-height: 45px; /* 세로로 3줄까지 허용 (3em은 폰트 크기의 3배) */
  overflow: hidden;
  white-space: normal; /* 세로로 3줄까지만 허용하기 위해 normal로 설정 */
  text-overflow: ellipsis; /* 가로로는 말줄임표를 표시하도록 유지 */
}
</style>
