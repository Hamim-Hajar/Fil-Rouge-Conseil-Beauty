export interface ArticleDto {
  id: number;
  titre: string;
  contenu: string;
  datePublication: Date; // Use Date for LocalDateTime equivalent
  image?: string; // Optional, as it is nullable in your entity
  specialistId: number; // Representing the relation with Specialist


}
