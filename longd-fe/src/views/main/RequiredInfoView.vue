<template>
  <div>
    <p>
      user_id :
      <input type="text" class="input-text" v-model="Info_state.userId" />
    </p>
    <p>
      생일(8자리) :
      <input type="text" class="input-text" v-model="Info_state.birth" />
    </p>
    <p>
      이메일(도메인포함) :
      <input type="text" class="input-text" v-model="Info_state.email" />
    </p>
    <p>
      성별 :
      <input type="text" class="input-text" v-model="Info_state.gender" />
    </p>
    <p>
      이름 : <input type="text" class="input-text" v-model="Info_state.name" />
    </p>
    <p>
      거주국가 :
      <input
        type="text"
        class="input-text"
        v-model="Info_state.addressNation"
      />
    </p>
    <p>
      권한 : <input type="text" class="input-text" value="ROLE_USER" disabled />
    </p>
    <p>
      provider :
      <input type="text" class="input-text" v-model="Info_state.provider" />
    </p>

    <button @click="send">정보 가입 해버리기</button>
<<<<<<< HEAD:longd-fe/src/views/main/RequiredInfoView.vue
=======
    {{ Info_state }}
>>>>>>> geunryeol:전근렬/longd-fe/src/views/main/RequiredInfoView.vue
  </div>
</template>

<script setup>
import { BaseInfo, sendinfo } from '@/utils/api/user';
import { useRouter } from 'vue-router';
import { ref, onMounted } from 'vue';

const router = useRouter();

const Info_state = ref({});

onMounted(() => {
  BaseInfo(
    data => {
      Info_state.value = data.data;
    },
    error => {
      console.log('Base Info 가져오기 안됨', error);
    },
  );
});

const send = () => {
  sendinfo(
    Info_state.value,
    data => {
      //success
      console.log('sendinfo 성공');
<<<<<<< HEAD:longd-fe/src/views/main/RequiredInfoView.vue
      router.push({ name: 'ConnectCode' });
=======
      router.push({ name: 'loginPage' });
>>>>>>> geunryeol:전근렬/longd-fe/src/views/main/RequiredInfoView.vue
    },
    error => {
      console.log('sendinfo 오류 : ' + error);
    },
  );
};
</script>

<<<<<<< HEAD:longd-fe/src/views/main/RequiredInfoView.vue
<style scoped>
input {
  border: 1px black solid;
}
button {
  border: 1px black solid;
}
</style>
=======
<style scoped></style>
>>>>>>> geunryeol:전근렬/longd-fe/src/views/main/RequiredInfoView.vue
