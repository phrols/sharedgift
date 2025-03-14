import { HttpError, SharedGiftAPI } from './common-api'
import type { FamilyAdmin } from './family-admin-api'

interface Token {
  token: string
}

const api = new SharedGiftAPI()

export class LoginAPI {
  login(familyAdmin: FamilyAdmin): Promise<void> {
    return api
      .post<Token>('/api/auth/login', familyAdmin, false)
      .then((res: Token) => localStorage.setItem('jwt-token', res.token))
  }
}
