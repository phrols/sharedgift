<script setup lang="ts">
import { FloatLabel, InputText } from 'primevue';
import InputGroup from 'primevue/inputgroup';
import InputGroupAddon from 'primevue/inputgroupaddon';
import Button from 'primevue/button';
import { computed, ref } from 'vue';
import { LoginAPI } from '@/api/login-api';
import { HttpError } from '@/api/common-api';
import { useRouter } from 'vue-router';

const email = ref<string>('');
const password = ref<string>('');
const isLoginDisabled = computed(()=> !email.value || !password.value);
const errorMsg = ref<string>('');

const router = useRouter();
const api = new LoginAPI();

const doLogin = async () => {
  try {
    await api.login({email: email.value, password:password.value});
    email.value = '';
    password.value = '';
    router.push({name: 'dashboard'});
  } catch (error) {
      if (error instanceof HttpError) {
        if (error.status === 401) {
          errorMsg.value = 'Email ou mot de passe incorrect';
        } else {
          errorMsg.value = 'Une erreur est survenue';
        }
    } else {
      errorMsg.value = 'Une erreur est survenue';
    }
  }
}
</script>

<template>
  <div class="login">
    <h2 class="blue">Connectez vous !</h2>
    <div class="flex flex-column row-gap-5">
    <InputGroup>
        <InputGroupAddon>
            <i class="pi pi-inbox"></i>
        </InputGroupAddon>
        <FloatLabel>
          <InputText id="email" v-model="email"/>
          <label for="email">Email</label>
        </FloatLabel>
    </InputGroup>
    <InputGroup>
        <InputGroupAddon>
            <i class="pi pi-lock"></i>
        </InputGroupAddon>
        <FloatLabel>
          <InputText id="password"  type="password" v-model="password"/>
          <label for="password">Mot de passe</label>
        </FloatLabel>
    </InputGroup>
    <Button label="Je me connecte" id="login" :disabled="isLoginDisabled" @click="doLogin"/>
    <p class="error">{{errorMsg}}</p>
    </div>
</div>
</template>

<style scoped>
.login {
  margin: 0 auto;
  max-width: 400px;
  display: flex;
  flex-direction: column;
}

h2 {
  font-weight: 700;
  text-align: center;
  margin-bottom: 2rem;
}
.error {
  color: red;
  text-align: center;
}
</style>
