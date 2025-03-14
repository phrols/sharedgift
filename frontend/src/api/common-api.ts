export class HttpError extends Error {
    status: number;

    constructor(status: number, message: string) {
        super(message);
        this.status = status;
        this.name = 'HttpError';
    }
}

const baseUrl = 'http://localhost:9000';
export class SharedGiftAPI {

    private callAPI<T>(path: string, method: string, connected: boolean, body?: any): Promise<T> {
        const options: RequestInit = {
            method: method,
            headers: {
                'Content-Type': 'application/json',
            },
        };

        if (body) {
            options.body = JSON.stringify(body);
        }

        if (connected) {
            const token = localStorage.getItem('jwt-token');
            if (token) {
                options.headers = {
                    ...options.headers,
                    Authorization: `Bearer ${token}`,
                };
            }
        }

        return fetch(`${baseUrl}${path}`, options).then((res: Response) => {
              if (!res.ok) { 
                return res.json().then((error) => {
                  throw new HttpError(error.status, error.error);
                });
              }
            return res.json();
        }).then((res: T) => res);
    }

    get<T>(path: string, connected = true): Promise<T> {
        return this.callAPI<T>(path, 'GET', connected);
    }

    post<T>(path: string, body: any, connected = true): Promise<T> {
        return this.callAPI<T>(path, 'POST', connected, body);
    }
}