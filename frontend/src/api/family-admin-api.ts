import { HttpError, SharedGiftAPI } from './common-api'

export interface FamilyAdmin {
  name?: string
  email: string
  password?: string
}

const api = new SharedGiftAPI()

export class FamilyAdminAPI {
  createFamilyAdmin(familyAdmin: FamilyAdmin): Promise<FamilyAdmin> {
    return api.post<FamilyAdmin>('/api/familyadmin', familyAdmin, false)
  }

  getConnectedFamilyAdmin() : Promise<FamilyAdmin> {
    return api.get<FamilyAdmin>('/api/familyadmin/me')
  }
}
