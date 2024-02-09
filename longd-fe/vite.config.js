import { fileURLToPath, URL } from 'node:url';

import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';

export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    },
  },
  server: {
    hmr: {
      host: 'i10d206.p.ssafy.io',
      port: 443, // SSL을 사용한다면 443, 그렇지 않다면 80 또는 필요한 포트로 설정
      protocol: 'wss', // SSL을 사용한다면 'wss', 그렇지 않다면 'ws'
    }
  }
});
