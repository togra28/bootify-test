import path from 'node:path';
import autoprefixer from 'autoprefixer';
import { defineConfig } from 'vite';


export default defineConfig({
  plugins: [{
    name: 'thymeleaf-full-reload',
    handleHotUpdate({ file, server }) {
      if (file.endsWith('.html')) {
        server.ws.send({ type: 'full-reload' });
      }
    }
  }],
  css: {
    postcss: {
      plugins: [autoprefixer()]
    }
  },
  build: {
    outDir: path.resolve(import.meta.dirname, './build/resources/main/static'),
    emptyOutDir: true,
    copyPublicDir: false,
    manifest: true,
    cssCodeSplit: false,
    rollupOptions: {
      input: path.resolve(import.meta.dirname, 'src/main/resources/js/app.js'),
      output: {
        entryFileNames: 'js/bundle-[hash].js',
        assetFileNames: 'css/bundle-[hash].[ext]'
      }
    }
  },
  server: {
    port: 8081,
    strictPort: true,
    open: true,
    proxy: {
      '^/(?!@vite|@id|@fs|node_modules|src/main/resources)': {
        target: 'http://localhost:8080',
        headers: {
          'X-Devserver': '1'
        }
      }
    }
  }
});
