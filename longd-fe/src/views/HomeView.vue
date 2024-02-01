<!-- <template>
  <div class="bg-white">
    <header class="absolute inset-x-0 top-0 z-50">
      <nav class="flex items-center justify-between p-6 lg:px-8" aria-label="Global">
        <div class="flex lg:flex-1">
          <a href="#" class="-m-1.5 p-1.5">
            <span class="sr-only">Your Company</span>
            <img class="h-8 w-auto" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600" alt="" />
          </a>
        </div>
        <div class="flex lg:hidden">
          <button type="button" class="-m-2.5 inline-flex items-center justify-center rounded-md p-2.5 text-gray-700" @click="mobileMenuOpen = true">
            <span class="sr-only">Open main menu</span>
            <Bars3Icon class="h-6 w-6" aria-hidden="true" />
          </button>
        </div>
        <div class="hidden lg:flex lg:gap-x-12">
          <a v-for="item in navigation" :key="item.name" :href="item.href" class="text-sm font-semibold leading-6 text-gray-900">{{ item.name }}</a>
        </div>
        <div class="hidden lg:flex lg:flex-1 lg:justify-end">
          <a href="#" class="text-sm font-semibold leading-6 text-gray-900">Log in <span aria-hidden="true">&rarr;</span></a>
        </div>
      </nav>
      <Dialog as="div" class="lg:hidden" @close="mobileMenuOpen = false" :open="mobileMenuOpen">
        <div class="fixed inset-0 z-50" />
        <DialogPanel class="fixed inset-y-0 right-0 z-50 w-full overflow-y-auto bg-white px-6 py-6 sm:max-w-sm sm:ring-1 sm:ring-gray-900/10">
          <div class="flex items-center justify-between">
            <a href="#" class="-m-1.5 p-1.5">
              <span class="sr-only">Your Company</span>
              <img class="h-8 w-auto" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600" alt="" />
            </a>
            <button type="button" class="-m-2.5 rounded-md p-2.5 text-gray-700" @click="mobileMenuOpen = false">
              <span class="sr-only">Close menu</span>
              <XMarkIcon class="h-6 w-6" aria-hidden="true" />
            </button>
          </div>
          <div class="mt-6 flow-root">
            <div class="-my-6 divide-y divide-gray-500/10">
              <div class="space-y-2 py-6">
                <a v-for="item in navigation" :key="item.name" :href="item.href" class="-mx-3 block rounded-lg px-3 py-2 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50">{{ item.name }}</a>
              </div>
              <div class="py-6">
                <a href="#" class="-mx-3 block rounded-lg px-3 py-2.5 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50">Log in</a>
              </div>
            </div>
          </div>
        </DialogPanel>
      </Dialog>
    </header>
    <div class="relative isolate px-6 pt-14 lg:px-8">
      <div class="absolute inset-x-0 -top-40 -z-10 transform-gpu overflow-hidden blur-3xl sm:-top-80" aria-hidden="true">
        <div class="relative left-[calc(50%-11rem)] aspect-[1155/678] w-[36.125rem] -translate-x-1/2 rotate-[30deg] bg-gradient-to-tr from-[#ff80b5] to-[#9089fc] opacity-30 sm:left-[calc(50%-30rem)] sm:w-[72.1875rem]" style="clip-path: polygon(74.1% 44.1%, 100% 61.6%, 97.5% 26.9%, 85.5% 0.1%, 80.7% 2%, 72.5% 32.5%, 60.2% 62.4%, 52.4% 68.1%, 47.5% 58.3%, 45.2% 34.5%, 27.5% 76.7%, 0.1% 64.9%, 17.9% 100%, 27.6% 76.8%, 76.1% 97.7%, 74.1% 44.1%)" />
      </div>
      <div class="mx-auto max-w-2xl py-32 sm:py-48 lg:py-56">
        <div class="hidden sm:mb-8 sm:flex sm:justify-center">
          <div class="relative rounded-full px-3 py-1 text-sm leading-6 text-gray-600 ring-1 ring-gray-900/10 hover:ring-gray-900/20">
            Announcing our next round of funding. <a href="#" class="font-semibold text-indigo-600"><span class="absolute inset-0" aria-hidden="true" />Read more <span aria-hidden="true">&rarr;</span></a>
          </div>
        </div>
        <div class="text-center">
          <h1 class="text-4xl font-bold tracking-tight text-gray-900 sm:text-6xl">Data to enrich your online business</h1>
          <p class="mt-6 text-lg leading-8 text-gray-600">Anim aute id magna aliqua ad ad non deserunt sunt. Qui irure qui lorem cupidatat commodo. Elit sunt amet fugiat veniam occaecat fugiat aliqua.</p>
          <div class="mt-10 flex items-center justify-center gap-x-6">
            <a href="#" class="rounded-md bg-indigo-600 px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Get started</a>
            <a href="#" class="text-sm font-semibold leading-6 text-gray-900">Learn more <span aria-hidden="true">→</span></a>
          </div>
        </div>
      </div>
      <div class="absolute inset-x-0 top-[calc(100%-13rem)] -z-10 transform-gpu overflow-hidden blur-3xl sm:top-[calc(100%-30rem)]" aria-hidden="true">
        <div class="relative left-[calc(50%+3rem)] aspect-[1155/678] w-[36.125rem] -translate-x-1/2 bg-gradient-to-tr from-[#ff80b5] to-[#9089fc] opacity-30 sm:left-[calc(50%+36rem)] sm:w-[72.1875rem]" style="clip-path: polygon(74.1% 44.1%, 100% 61.6%, 97.5% 26.9%, 85.5% 0.1%, 80.7% 2%, 72.5% 32.5%, 60.2% 62.4%, 52.4% 68.1%, 47.5% 58.3%, 45.2% 34.5%, 27.5% 76.7%, 0.1% 64.9%, 17.9% 100%, 27.6% 76.8%, 76.1% 97.7%, 74.1% 44.1%)" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { Dialog, DialogPanel } from '@headlessui/vue'
import { Bars3Icon, XMarkIcon } from '@heroicons/vue/24/outline'

const navigation = [
  { name: 'Product', href: '#' },
  { name: 'Features', href: '#' },
  { name: 'Marketplace', href: '#' },
  { name: 'Company', href: '#' },
]

const mobileMenuOpen = ref(false)
</script> -->

<template>
  <div class="box">
    <div class="group">
      <div class="overlap">
        <div class="view">
          <div class="overlap-group">
            <div class="overlap-group-wrapper">
              <div class="div">
                <div class="rectangle" />
                <div class="overlap-2">
                  <div class="rectangle-2" />
                  <div class="text-wrapper">S</div>
                </div>
                <div class="rectangle-3" />
                <div class="overlap-3">
                  <div class="rectangle-4" />
                  <div class="text-wrapper-2">M</div>
                </div>
                <div class="rectangle-5" />
                <div class="overlap-4">
                  <div class="rectangle-2" />
                  <div class="text-wrapper">T</div>
                </div>
                <div class="rectangle-6" />
                <div class="overlap-5">
                  <div class="rectangle-2" />
                  <div class="text-wrapper-2">T</div>
                </div>
                <div class="rectangle-7" />
                <div class="overlap-6">
                  <div class="rectangle-2" />
                  <div class="text-wrapper-2">F</div>
                </div>
                <div class="rectangle-8" />
                <div class="overlap-7">
                  <div class="rectangle-2" />
                  <div class="text-wrapper-2">S</div>
                </div>
                <div class="rectangle-9" />
                <div class="overlap-8">
                  <div class="rectangle-2" />
                  <div class="text-wrapper-2">W</div>
                </div>
              </div>
            </div>
            <div class="group-2">
              <img
                class="ellipse"
                alt="Ellipse"
                src="/static/img/ellipse-658.png"
              />
              <img
                class="img"
                alt="Ellipse"
                src="/static/img/ellipse-659.png"
              />
              <div class="image">
                <img
                  class="heart-suit"
                  alt="Heart suit"
                  src="/static/img/heart-suit.png"
                />
              </div>
            </div>
          </div>
        </div>
        <div class="overlap-wrapper">
          <div class="div-wrapper">
            <div class="text-wrapper-3">D+365</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { loginstate } from '@/utils/api/user';
import { useRouter } from 'vue-router';

const router = useRouter();

onMounted(() => {
  loginstate(
    success => {
      if (success.data === '롱디에 로그인 되어 있지 않음') {
        //     홈 실행시 로그인 여부를 체크해서 안되있으면 로그인 화면으로 팅궈냅니다
        //     '롱디에 로그인 되어 있지 않음' <<< 요거 문구 수정하면안됩니다 문구에 반응하는거임
        console.log('로그인 안되어있다.');
        router.push({ name: 'Login' });
      } else {
        console.log('로그인 되어있다', success.data);
      }
    },
    error => {
      console.log('error') + error;
    },
  );
});
</script>

<style scoped>
.box {
  height: 740px;
  width: 612px;
}

/* .box .group {
  height: 740px;
  left: 0;
  position: fixed;
  top: 0;
  width: 612px;
} */

/* .box .overlap {
  height: 740px;
  position: relative;
} */

.box .view {
  height: 720px;
  left: 50px;
  position: absolute;
  top: 200px;
  width: 612px;
}

.box .overlap-group {
  background-image: url(/static/img/frame.png);
  background-size: 100% 100%;
  height: 728px;
  left: -4px;
  position: relative;
  width: 620px;
}

.box .overlap-group-wrapper {
  height: 94px;
  left: 33px;
  position: absolute;
  top: 607px;
  width: 568px;
}

.box .div {
  -webkit-backdrop-filter: blur(31.21px) brightness(100%);
  backdrop-filter: blur(31.21px) brightness(100%);
  background-color: #ffffff8a;
  border: 0.78px solid;
  border-color: #ffffff;
  height: 94px;
  position: relative;
  width: 554px;
}

.box .rectangle {
  background-color: #ffffff;
  height: 57px;
  left: 4px;
  position: absolute;
  top: 31px;
  width: 75px;
}

.box .overlap-2 {
  height: 24px;
  left: 5px;
  position: absolute;
  top: 4px;
  width: 75px;
}

.box .rectangle-2 {
  background-color: #ebebeb;
  height: 23px;
  left: 0;
  position: absolute;
  top: 0;
  width: 75px;
}

.box .text-wrapper {
  color: #000000;
  font-family: 'K2D-Bold', Helvetica;
  font-size: 15px;
  font-weight: 700;
  left: 26px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  top: 2px;
  width: 25px;
}

.box .rectangle-3 {
  background-color: #ffffff;
  height: 57px;
  left: 83px;
  position: absolute;
  top: 31px;
  width: 74px;
}

.box .overlap-3 {
  height: 24px;
  left: 84px;
  position: absolute;
  top: 4px;
  width: 74px;
}

.box .rectangle-4 {
  background-color: #ebebeb;
  height: 23px;
  left: 0;
  position: absolute;
  top: 0;
  width: 74px;
}

.box .text-wrapper-2 {
  color: #000000;
  font-family: 'K2D-Bold', Helvetica;
  font-size: 15px;
  font-weight: 700;
  left: 25px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  top: 2px;
  width: 25px;
}

.box .rectangle-5 {
  background-color: #ffffff;
  height: 57px;
  left: 160px;
  position: absolute;
  top: 31px;
  width: 75px;
}

.box .overlap-4 {
  height: 24px;
  left: 161px;
  position: absolute;
  top: 4px;
  width: 75px;
}

.box .rectangle-6 {
  background-color: #ffffff;
  height: 57px;
  left: 317px;
  position: absolute;
  top: 31px;
  width: 75px;
}

.box .overlap-5 {
  height: 24px;
  left: 318px;
  position: absolute;
  top: 4px;
  width: 75px;
}

.box .rectangle-7 {
  background-color: #ffffff;
  height: 57px;
  left: 395px;
  position: absolute;
  top: 31px;
  width: 75px;
}

.box .overlap-6 {
  height: 24px;
  left: 396px;
  position: absolute;
  top: 4px;
  width: 75px;
}

.box .rectangle-8 {
  background-color: #ffffff;
  height: 57px;
  left: 473px;
  position: absolute;
  top: 31px;
  width: 75px;
}

.box .overlap-7 {
  height: 24px;
  left: 474px;
  position: absolute;
  top: 4px;
  width: 75px;
}

.box .rectangle-9 {
  background-color: #ffffff;
  height: 57px;
  left: 239px;
  position: absolute;
  top: 31px;
  width: 75px;
}

.box .overlap-8 {
  height: 24px;
  left: 240px;
  position: absolute;
  top: 4px;
  width: 75px;
}

.box .group-2 {
  height: 80px;
  left: 210px;
  position: absolute;
  top: 70px;
  width: 216px;
}

.box .ellipse {
  height: 80px;
  left: 136px;
  position: absolute;
  top: 0;
  width: 80px;
}

.image .heart-suit {
  height: 43px;
  left: 85px;
  object-fit: cover;
  position: relative;
  top: 20px;
  width: 42px;
}
.box .img {
  height: 80px;
  left: 0;
  position: absolute;
  top: 0;
  width: 80px;
}

.box .overlap-wrapper {
  height: 78px;
  left: 230px;
  position: absolute;
  top: 180px;
  width: 266px;
}

.box .div-wrapper {
  background-image: url(/static/img/group-19.png);
  background-size: 100% 100%;
  height: 78px;
  position: relative;
  width: 264px;
}

.box .text-wrapper-3 {
  color: #ffffff;
  font-family: 'Jura-SemiBold', Helvetica;
  font-size: 50px;
  font-weight: 600;
  left: 39px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  text-shadow: 0px 4px 4px #00000040;
  top: 11px;
  white-space: nowrap;
  width: 182px;
}
</style>
