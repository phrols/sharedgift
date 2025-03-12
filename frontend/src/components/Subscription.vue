<script setup lang="ts">
import { FloatLabel, InputText } from 'primevue';
import InputGroup from 'primevue/inputgroup';
import InputGroupAddon from 'primevue/inputgroupaddon';
import Button from 'primevue/button';
import { computed, ref } from 'vue';
import { createFamilyAdmin, type FamilyAdmin } from '@/api/family-admin';
import { HttpError } from '@/api/api-common';

const name = ref<string>('');
const email = ref<string>('');
const password = ref<string>('');
const isSubribtionDisabled = computed(()=>!name.value || !email.value || !password.value);
const errorMsg = ref<string>('');
const successMsg = ref<string>('');

const handleSubscription = async () => {
  try {
    await createFamilyAdmin({name: name.value, email: email.value, password:password.value});
    errorMsg.value = '';
    successMsg.value = 'Votre compte a bien été créé ' + name.value + '!'; 
    name.value = '';
    email.value = '';
    password.value = '';
  } catch (error) {
      if (error instanceof HttpError) {
        if (error.status === 409) {
          errorMsg.value = 'Un compte existe déjà avec cet email';
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
  <div class="subscription">
    <h2 class="blue">Inscrivez-vous dès maintenant pour pouvoir partager vos cadeaux !</h2>
    <div class="flex flex-column row-gap-5">
      <InputGroup>
        <InputGroupAddon>
            <i class="pi pi-user"></i>
        </InputGroupAddon>
        <FloatLabel>
          <InputText id="name" v-model="name"/>
          <label for="name">Nom</label>
        </FloatLabel>
    </InputGroup>
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
    <Button label="Je m'inscris" :disabled="isSubribtionDisabled" @click="handleSubscription"/>
    <p class="error" v-if="errorMsg">{{ errorMsg }}</p>
    <p class="success blue" v-if="!errorMsg && successMsg">Votre compte a bien été créé !</p>
    </div>
</div>
</template>

<style scoped>
.subscription {
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
.success {
  text-align: center;
  font-weight: 700;
}
</style>
