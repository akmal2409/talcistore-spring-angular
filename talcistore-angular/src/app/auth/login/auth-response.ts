export interface AuthResponse {
  expiresAt: Date;
  refreshToken: string;
  token: string;
  username: string;
}
