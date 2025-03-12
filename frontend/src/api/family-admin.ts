import { HttpError } from "./api-common";


export interface FamilyAdmin {
    name: string;
    email: string;
    password: string;
  }

  export function createFamilyAdmin(familyAdmin: FamilyAdmin): Promise<FamilyAdmin> {
    return fetch('http://localhost:9000/api/familyadmin', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(familyAdmin),
    }).then((res: Response) => {
        if (!res.ok) {
            return res.json().then((error) => {
                throw new HttpError(res.status, error.message);
            });
        }
        return res.json();
    }).then((res: FamilyAdmin) => res);
  }